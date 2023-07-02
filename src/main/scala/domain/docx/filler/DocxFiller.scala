package fr.valle.report_generator
package domain.docx.filler

import logging.{Levels, Log, LogsKeeper}

import org.apache.logging.log4j.scala.Logging
import org.apache.poi.xwpf.usermodel._

import scala.jdk.CollectionConverters.CollectionHasAsScala
import scala.util.{Failure, Success, Try}

class DocxFiller extends Logging {
  def fillDocx(templateDoc: XWPFDocument, valuesMap: Map[String, String]): XWPFDocument = {
    LogsKeeper.keepAndLog(extLogger = logger, log = Log(message = "fillDocx()", level = Levels.INFO), classFrom = getClass)

    val filledTemplateDoc: XWPFDocument = templateDoc

    tryFillParagraphs(templateDoc = templateDoc, valuesMap = valuesMap) match {
      case Success(_) =>
      case Failure(exception) => throw exception
    }
    tryFillFooters(templateDoc = templateDoc, valuesMap = valuesMap) match {
      case Success(_) =>
      case Failure(exception) => throw exception
    }
    tryFillTablesSafely(templateDoc = templateDoc, valuesMap = valuesMap) match {
      case Success(_) =>
      case Failure(exception) => throw exception
    }

    filledTemplateDoc
  }

  private def tryFillParagraphs(templateDoc: XWPFDocument, valuesMap: Map[String, String]): Try[Unit] = {
    Try {
      fillParagraphs(templateDoc = templateDoc, valuesMap = valuesMap)
    }
  }

  private def fillParagraphs(templateDoc: XWPFDocument, valuesMap: Map[String, String]): Unit = {
    // Loop through all the paragraphs in the document and replace the values
    for (para: XWPFParagraph <- templateDoc.getParagraphs.asScala) {
      replaceText(valuesMap, para.getRuns.asScala)
    }
  }

  private def tryFillFooters(templateDoc: XWPFDocument, valuesMap: Map[String, String]): Try[Unit] = {
    Try {
      fillFooters(templateDoc = templateDoc, valuesMap = valuesMap)
    }
  }

  private def fillFooters(templateDoc: XWPFDocument, valuesMap: Map[String, String]): Unit = {
    // Loop through all the footers in the document and replace the values
    for (footer: XWPFFooter <- templateDoc.getFooterList.asScala) {
      for (para: XWPFParagraph <- footer.getParagraphs.asScala) {
        replaceText(valuesMap, para.getRuns.asScala)
      }
    }
  }

  private def replaceText(valuesMap: Map[String, String], runs: Iterable[XWPFRun]): Unit = {
    for (run: XWPFRun <- runs) {
      try {
        var text = run.getText(0)
        if (text != null) {
          // Replace all occurrences of the values with the corresponding values in the map
          for ((key, value) <- valuesMap) {
            text = text.replace(key, value)
          }
          // Set the new text in the run
          run.setText(text, 0)
        }
      } catch {
        case _: Throwable => LogsKeeper.keepAndLog(extLogger = logger, log = Log(message = s"No text in run", level = Levels.WARN), classFrom = getClass)
      }
    }
  }

  private def tryFillTablesSafely(templateDoc: XWPFDocument, valuesMap: Map[String, String]): Try[Unit] = {
    Try {
      fillTables(templateDoc = templateDoc, valuesMap = valuesMap)
    }
  }

  private def fillTables(templateDoc: XWPFDocument, valuesMap: Map[String, String]): Unit = {
    for (table: XWPFTable <- templateDoc.getTables.asScala) {
      for (row: XWPFTableRow <- table.getRows.asScala) {
        // Loop through the cells of the row
        for (cell: XWPFTableCell <- row.getTableCells.asScala) {
          var text = cell.getText
          if (text != null) {
            // Replace all occurrences of the values with the corresponding values in the map
            for ((key, value) <- valuesMap) {
              text = text.replace(key, value)
            }
            // Set the new text in the cell
            try {
              cell.getParagraphs.get(0).getRuns.get(0).setText(text, 0)
            } catch {
              case _: Throwable => LogsKeeper.keepAndLog(extLogger = logger, log = Log(message = s"A cell of a table is empty", level = Levels.WARN), classFrom = getClass)
            }
          }
        }
      }
    }
  }
}

object DocxFiller {
  def apply(): DocxFiller = new DocxFiller()
}
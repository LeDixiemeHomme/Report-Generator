package fr.valle.report_generator
package domain.parser

import logging.LogsKeeper

import org.apache.logging.log4j.scala.Logging
import org.apache.poi.xwpf.usermodel.{XWPFDocument, XWPFParagraph}

import java.io.FileInputStream
import java.util
import scala.collection.convert.ImplicitConversions.`list asScalaBuffer`

class DocxParser extends Logging {
  def parseDocx(filePath: String): String = {

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.INFO, "Parsing docx " + filePath, classFrom = getClass)

    val document = new XWPFDocument(new FileInputStream(filePath))
    val paragraphs: util.List[XWPFParagraph] = document.getParagraphs
    val text = paragraphs.map(_.getText).mkString("\n")
    document.close()

    text
  }
}

object DocxParser {
  def apply(): DocxParser = new DocxParser()
}

package fr.valle.report_generator
package domain.reader

import customexceptions.TemplateFileNotFoundException
import logging.LogsKeeper

import org.apache.logging.log4j.scala.Logging
import org.apache.poi.xwpf.usermodel.XWPFDocument

import java.io.{File, FileInputStream, FileNotFoundException}
import scala.util.{Failure, Success, Try}

class DocxReader extends Logging {

  /**
   * @throws TemplateFileNotFoundException if the `templateFilePath` is not actually an existing file
   */
  @throws(classOf[TemplateFileNotFoundException])
  def readDocx(templateFilePath: String): XWPFDocument = {

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.INFO, "Reading docx " + templateFilePath, classFrom = getClass)

    // Read the template file into a XWPFDocument object
    val templateFile: File = new File(templateFilePath)

    val templateDoc: XWPFDocument = tryReadDocxSafely(templateFile = templateFile) match {
      case Success(templateDoc: XWPFDocument) => templateDoc
      case Failure(fileNotFoundException: FileNotFoundException) => throw new TemplateFileNotFoundException(filePath = templateFilePath, cause = Some(fileNotFoundException))
    }

    templateDoc
  }

  private def tryReadDocxSafely(templateFile: File): Try[XWPFDocument] = {
    Try {
      val templateStream: FileInputStream = new FileInputStream(templateFile)

      try {
        new XWPFDocument(templateStream)
      } finally {
        templateStream.close()
      }
    }
  }
}

object DocxReader {
  def apply(): DocxReader = new DocxReader()
}
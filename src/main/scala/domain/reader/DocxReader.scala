package fr.valle.report_generator
package domain.reader

import logging.LogsKeeper

import org.apache.logging.log4j.scala.Logging
import org.apache.poi.xwpf.usermodel.XWPFDocument

import java.io.{File, FileInputStream}

class DocxReader extends Logging {

  def readDocx(templateFilePath: String): XWPFDocument = {

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.INFO, "Reading docx " + templateFilePath, classFrom = getClass)

    // Read the template file into a XWPFDocument object
    val templateFile: File = new File(templateFilePath)
    val templateStream: FileInputStream = new FileInputStream(templateFile)
    val templateDoc: XWPFDocument = new XWPFDocument(templateStream)

    templateDoc
  }
}

object DocxReader {
  def apply(): DocxReader = new DocxReader()
}
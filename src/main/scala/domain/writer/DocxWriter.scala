package fr.valle.report_generator
package domain.writer

import logging.LogsKeeper

import org.apache.logging.log4j.scala.Logging
import org.apache.poi.xwpf.usermodel.XWPFDocument

import java.io.FileOutputStream

class DocxWriter extends Logging with IsAWriterTrait {
  override def fileExtension() = ".docx"

  def write(templateDoc: XWPFDocument, outputDirPath: String, fileName: String): String = {

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.INFO, "Writing " + fileName + " docx to " + outputDirPath, classFrom = getClass)

    val finalPath = constructFinalPath(outputDirPath = outputDirPath, fileName = fileName, fileExtension = fileExtension())

    // Write the modified document to the output file
    val outputStream = new FileOutputStream(finalPath)
    try {
      templateDoc.write(outputStream)
      outputStream.close()
      s"Successfully written in $outputDirPath"
    }
  }
}

object DocxWriter {
  def apply(): DocxWriter = new DocxWriter()
}
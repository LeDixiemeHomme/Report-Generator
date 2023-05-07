package fr.valle.report_generator
package domain.writer

import logging.LogsKeeper

import org.apache.logging.log4j.scala.Logging
import org.apache.poi.xwpf.usermodel.XWPFDocument

import java.io.FileOutputStream

class DocxWriter extends Logging {
  def write(templateDoc: XWPFDocument, outputDirPath: String, fileName: String): String = {

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.INFO, "Writing " + fileName + " docx to " + outputDirPath, classFrom = getClass)

    var finalPath = outputDirPath + fileName

    val outputDirPathHasSlashAtEnd = outputDirPath.last == '\\' || outputDirPath.last == '/'
    val fileNameHasSlashAtBeginning = fileName.charAt(0) == '\\' || fileName.charAt(0) == '/'

    val bothHaveSlash = outputDirPathHasSlashAtEnd && fileNameHasSlashAtBeginning
    val neitherHaveSlash = !outputDirPathHasSlashAtEnd && !fileNameHasSlashAtBeginning

    if (bothHaveSlash || neitherHaveSlash) finalPath = outputDirPath + '/' + fileName

    val docxExtension = ".docx"

    if (!finalPath.endsWith(docxExtension)) finalPath = finalPath + docxExtension

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
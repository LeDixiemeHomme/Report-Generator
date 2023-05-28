package fr.valle.report_generator
package domain.writer

import customexceptions.OutputDirNotFoundException
import logging.LogsKeeper

import org.apache.logging.log4j.scala.Logging
import org.apache.poi.xwpf.usermodel.XWPFDocument

import java.io.{FileNotFoundException, FileOutputStream}
import scala.util.{Failure, Success, Try}

class DocxWriter extends Logging with IsAWriterTrait {
  override def fileExtension() = ".docx"

  /**
   * @throws OutputDirNotFoundException if the `outputDirPath` does not exists
   */
  @throws(classOf[OutputDirNotFoundException])
  def write(templateDoc: XWPFDocument, outputDirPath: String, fileName: String): String = {

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.INFO, "Writing " + fileName + " docx to " + outputDirPath, classFrom = getClass)

    val finalPath = constructFinalPath(outputDirPath = outputDirPath, fileName = fileName, fileExtension = fileExtension())

    val outputMessage: String = tryWriteDocxSafely(templateDoc = templateDoc, finalPath = finalPath) match {
      case Success(_) => s"Successfully written in $outputDirPath"

      case Failure(fileNotFoundException: FileNotFoundException) => throw new OutputDirNotFoundException(outputDirPath = outputDirPath, cause = Some(fileNotFoundException))
      case Failure(exception: Exception) => throw exception
    }

    outputMessage
  }

  private def tryWriteDocxSafely(templateDoc: XWPFDocument, finalPath: String): Try[Unit] = {
    Try {
      val outputStream: FileOutputStream = new FileOutputStream(finalPath)
      try {
        templateDoc.write(outputStream)
      } finally {
        outputStream.close()
      }
    }
  }
}

object DocxWriter {
  def apply(): DocxWriter = new DocxWriter()
}
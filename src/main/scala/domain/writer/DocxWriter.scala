package fr.valle.report_generator
package domain.writer

import customexceptions.OutputDirNotFoundException
import logging.LogsKeeper

import fr.valle.report_generator.domain.path.FilePath
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
  def write(templateDoc: XWPFDocument, outputFilePath: FilePath): WriteResult = {

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.INFO, "Writing " + outputFilePath.fileName.value + " docx to " + outputFilePath.basePath, classFrom = getClass)

    val finalPath = outputFilePath.constructFinalPath

    val outputMessage: WriteResult = tryWriteDocxSafely(templateDoc = templateDoc, finalPath = finalPath) match {
      case Success(_) => new WriteResult(outputPath = finalPath, outputMessage = s"Successfully written ${outputFilePath.fileName.value} in ${outputFilePath.basePath}")

      case Failure(fileNotFoundException: FileNotFoundException) => throw OutputDirNotFoundException(outputDirPath = outputFilePath.basePath, cause = Some(fileNotFoundException))
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
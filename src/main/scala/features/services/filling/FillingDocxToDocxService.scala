package fr.valle.report_generator
package features.services.filling

import customexceptions.{EmptyXWPFDocumentException, OutputDirNotFoundException, TemplateFileNotFoundException, WrongFileFormatException}
import domain.filler.DocxFiller
import domain.path.FilePath
import domain.reader.DocxReader
import domain.writer.{DocxWriter, WriteResult}
import features.results.FillingResult
import logging.LogsKeeper

import org.apache.logging.log4j.scala.Logging
import org.apache.poi.xwpf.usermodel.XWPFDocument

class FillingDocxToDocxService extends Logging with FillingServiceTrait {
  private val docxReader: DocxReader = DocxReader()
  private val docxFiller: DocxFiller = DocxFiller()
  private val docxWriter: DocxWriter = DocxWriter()

  private def defaultValue: String = {
    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.ERROR,
      "Something went wrong with the name of the file, using default value instead", classFrom = getClass)
    "default-name-value"
  }

  override def fill(templateFilePath: FilePath, valuesMap: Map[String, String], outputFilePath: FilePath): FillingResult = {

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.INFO, "Filling docx document", classFrom = getClass)

    var filledTemplateDoc: XWPFDocument = new XWPFDocument()
    var templateDoc: XWPFDocument = new XWPFDocument()

    try {
      templateDoc = docxReader.readDocx(templateFilePath = templateFilePath)
    } catch {
      case templateFileNotFoundException: TemplateFileNotFoundException =>
        LogsKeeper.handleError(extLogger = logger, exception = templateFileNotFoundException, classFrom = getClass)
        return FillingResult(isSuccess = false, popUpMessage = templateFileNotFoundException.getMessage, filledDocRelativePath = None)
      case emptyXWPFDocumentException: EmptyXWPFDocumentException =>
        LogsKeeper.handleError(extLogger = logger, exception = emptyXWPFDocumentException, classFrom = getClass)
        return FillingResult(isSuccess = false, popUpMessage = emptyXWPFDocumentException.getMessage, filledDocRelativePath = None)
      case wrongFileFormatException: WrongFileFormatException =>
        LogsKeeper.handleError(extLogger = logger, exception = wrongFileFormatException, classFrom = getClass)
        return FillingResult(isSuccess = false, popUpMessage = wrongFileFormatException.getMessage, filledDocRelativePath = None)
    }

    try {
      filledTemplateDoc = docxFiller.fillDocx(templateDoc = templateDoc, valuesMap = valuesMap)
    } catch {
      case emptyXWPFDocumentException: EmptyXWPFDocumentException =>
        LogsKeeper.handleError(extLogger = logger, exception = emptyXWPFDocumentException, classFrom = getClass)
        return FillingResult(isSuccess = false, popUpMessage = emptyXWPFDocumentException.getMessage, filledDocRelativePath = None)
    }

    var writeResult: WriteResult = new WriteResult("", "")

    try {
      writeResult = docxWriter.write(filledTemplateDoc, outputFilePath)
    } catch {
      case outputDirNotFoundException: OutputDirNotFoundException =>
        LogsKeeper.handleError(extLogger = logger, exception = outputDirNotFoundException, classFrom = getClass)
        return FillingResult(isSuccess = false, popUpMessage = outputDirNotFoundException.getMessage, filledDocRelativePath = None)
    }

    FillingResult(isSuccess = true, popUpMessage = writeResult.outputMessage, filledDocRelativePath = Some(outputFilePath))
  }
}

object FillingDocxToDocxService {
  def apply(): FillingDocxToDocxService = new FillingDocxToDocxService()
}

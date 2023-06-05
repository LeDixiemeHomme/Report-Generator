package fr.valle.report_generator
package features.services.filling

import customexceptions.{EmptyXWPFDocumentException, OutputDirNotFoundException, TemplateFileNotFoundException, WrongFileFormatException}
import domain.filler.DocxFiller
import domain.reader.DocxReader
import domain.writer.{DocxWriter, WriteResult}
import features.results.FillingResult
import logging.LogsKeeper

import fr.valle.report_generator.domain.path.FilePath
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

  def fill(templateFilePath: FilePath, valuesMap: Map[String, String], outputFilePath: String, optionalFileName: Option[String] = None): FillingResult = {

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.INFO, "Filling docx document", classFrom = getClass)

    var filledTemplateDoc: XWPFDocument = new XWPFDocument()
    var templateDoc: XWPFDocument = new XWPFDocument()

    val fileName = optionalFileName.getOrElse(defaultValue)

    try {
      templateDoc = docxReader.readDocx(templateFilePath = templateFilePath)
    } catch {
      case templateFileNotFoundException: TemplateFileNotFoundException =>
        LogsKeeper.handleError(extLogger = logger, exception = templateFileNotFoundException, classFrom = getClass)
        return FillingResult(isSuccess = false, popUpMessage = templateFileNotFoundException.getMessage, filledDocRelativePath = outputFilePath + fileName + ".docx", outputFilePath = outputFilePath)
      case emptyXWPFDocumentException: EmptyXWPFDocumentException =>
        LogsKeeper.handleError(extLogger = logger, exception = emptyXWPFDocumentException, classFrom = getClass)
        return FillingResult(isSuccess = false, popUpMessage = emptyXWPFDocumentException.getMessage, filledDocRelativePath = outputFilePath + fileName + ".docx", outputFilePath = outputFilePath)
    case wrongFileFormatException: WrongFileFormatException =>
        LogsKeeper.handleError(extLogger = logger, exception = wrongFileFormatException, classFrom = getClass)
        return FillingResult(isSuccess = false, popUpMessage = wrongFileFormatException.getMessage, filledDocRelativePath = outputFilePath + fileName + ".docx", outputFilePath = outputFilePath)
    }

    try {
      filledTemplateDoc = docxFiller.fillDocx(templateDoc = templateDoc, valuesMap = valuesMap)
    } catch {
      case emptyXWPFDocumentException: EmptyXWPFDocumentException =>
        LogsKeeper.handleError(extLogger = logger, exception = emptyXWPFDocumentException, classFrom = getClass)
        return FillingResult(isSuccess = false, popUpMessage = emptyXWPFDocumentException.getMessage, filledDocRelativePath = outputFilePath + fileName + ".docx", outputFilePath = outputFilePath)
    }

    var writeResult: WriteResult = new WriteResult("", "")

    try {
      writeResult = docxWriter.write(filledTemplateDoc, outputFilePath, fileName)
    } catch {
      case outputDirNotFoundException: OutputDirNotFoundException =>
        LogsKeeper.handleError(extLogger = logger, exception = outputDirNotFoundException, classFrom = getClass)
        return FillingResult(isSuccess = false, popUpMessage = outputDirNotFoundException.getMessage, filledDocRelativePath = outputFilePath + fileName + ".docx", outputFilePath = outputFilePath)
    }

    FillingResult(isSuccess = true, popUpMessage = writeResult.outputMessage, filledDocRelativePath = outputFilePath + fileName + ".docx", outputFilePath = writeResult.outputPath)
  }
}

object FillingDocxToDocxService {
  def apply(): FillingDocxToDocxService = new FillingDocxToDocxService()
}

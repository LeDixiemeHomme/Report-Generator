package fr.valle.report_generator
package services.filling

import customexceptions.EmptyXWPFDocumentException
import domain.filler.DocxFiller
import domain.reader.DocxReader
import domain.writer.DocxWriter
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

  def fill(templateFilePath: String, valuesMap: Map[String, String], outputFilePath: String, optionalFileName: Option[String] = None): FillingResult = {

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.INFO, "Filling docx document", classFrom = getClass)

    var filledTemplateDoc: XWPFDocument = new XWPFDocument()
    var templateDoc: XWPFDocument = new XWPFDocument()

    val fileName = optionalFileName.getOrElse(defaultValue)

    try {
      templateDoc = docxReader.readDocx(templateFilePath = templateFilePath)
    }

    try {
      filledTemplateDoc = docxFiller.fillDocx(templateDoc = templateDoc, valuesMap = valuesMap)
    } catch {
      case emptyXWPFDocumentException: EmptyXWPFDocumentException =>
        LogsKeeper.handleError(extLogger = logger, exception = emptyXWPFDocumentException, classFrom = getClass)
        return new FillingResult(completionMessage = emptyXWPFDocumentException.getMessage, filledDocRelativePath = outputFilePath + fileName + ".docx", outputFilePath = outputFilePath)
    }

    val result: String = docxWriter.write(filledTemplateDoc, outputFilePath, fileName)

    new FillingResult(completionMessage = result, filledDocRelativePath = outputFilePath + fileName + ".docx", outputFilePath = outputFilePath)
  }
}

object FillingDocxToDocxService {
  def apply(): FillingDocxToDocxService = new FillingDocxToDocxService()
}

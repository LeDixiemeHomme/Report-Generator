package fr.valle.report_generator
package services.filling

import domain.filler.DocxFiller
import domain.reader.DocxReader
import domain.writer.DocxWriter
import logging.LogsKeeper

import org.apache.logging.log4j.scala.Logging
import org.apache.poi.xwpf.usermodel.XWPFDocument

class FillingDocxToDocxService extends Logging with FillingServiceTrait {
  private val docxReader: DocxReader = new DocxReader
  private val docxFiller: DocxFiller = new DocxFiller
  private val docxWriter: DocxWriter = new DocxWriter

  private val defaultValue: String = {
    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.ERROR,
      "Something went wrong with the name of the file, using default value instead", classFrom = getClass)
    "default-value"
  }

  def fill(templateFilePath: String, valuesMap: Map[String, String], outputFilePath: String, optionalFileName: Option[String] = None): FillingResult = {

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.INFO, "Filling docx document", classFrom = getClass)

    val templateDoc: XWPFDocument = docxReader.readDocx(templateFilePath = templateFilePath)
    val filledTemplateDoc: XWPFDocument = docxFiller.fillDocx(templateDoc = templateDoc, valuesMap = valuesMap)
    val fileName = optionalFileName.getOrElse(defaultValue)

    val result: String = docxWriter.write(filledTemplateDoc, outputFilePath, fileName)

    new FillingResult(completionMessage = result)
  }
}

object FillingDocxToDocxService {
  def apply(): FillingDocxToDocxService = new FillingDocxToDocxService()
}

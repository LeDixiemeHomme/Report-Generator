package fr.valle.report_generator
package services.filling

import domain.filler.DocxFiller
import domain.reader.DocxReader
import domain.writer.DocxWriter

import org.apache.poi.xwpf.usermodel.XWPFDocument

class FillingDocxToDocxService extends FillingServiceTrait {
  private val docxReader: DocxReader = new DocxReader
  private val docxFiller: DocxFiller = new DocxFiller
  private val docxWriter: DocxWriter = new DocxWriter

  def fill(templateFilePath: String, valuesMap: Map[String, String], outputFilePath: String, optionalFileName: Option[String] = None): FillingResult = {
    val templateDoc: XWPFDocument = docxReader.readDocx(templateFilePath = templateFilePath)
    val filledTemplateDoc: XWPFDocument = docxFiller.fillDocx(templateDoc = templateDoc, valuesMap = valuesMap)

    val fileName = optionalFileName.getOrElse("default value")

    val result: String = docxWriter.write(filledTemplateDoc, outputFilePath, fileName)

    new FillingResult(completionMessage = result)
  }
}

object FillingDocxToDocxService {
  def apply(): FillingDocxToDocxService = new FillingDocxToDocxService()
}

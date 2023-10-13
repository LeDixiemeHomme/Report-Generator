package fr.valle.report_generator

import app.path.FilePath
import domain.ReportPOIXWPFDocument
import domain.docx.reader.DocxReader
import domain.docx.writer.DocxWriter
import features.services.OpenDocxReport

import org.apache.poi.xwpf.usermodel.XWPFDocument

import java.nio.file.Paths

object MainColumnWork extends App {
  val docxReader = DocxReader()
  val docxWriter = DocxWriter()
  val openDocxReport = OpenDocxReport()

  val pathRes = "src/test/resources"

  val baseDoc = docxReader.readDocx(templateFilePath = FilePath.stringToFilePath(
    //        Paths.get(pathRes, "Exemple rapport type réception.docx").toString)))
    Paths.get(pathRes, "template-test-add-column.docx").toString))

  val report = new ReportPOIXWPFDocument(document = baseDoc)

  report.addRowToTableSorbonnes()
  report.addColumnToTableSorbonnes()

  val updatedDoc: XWPFDocument = report.document

  val writeResult = docxWriter.write(templateDoc = updatedDoc, FilePath.stringToFilePath(
    Paths.get(pathRes, "template-test-add-column-updated.docx").toString))

  println(writeResult)
  openDocxReport.open(fileLocation = writeResult.outputPath)
}

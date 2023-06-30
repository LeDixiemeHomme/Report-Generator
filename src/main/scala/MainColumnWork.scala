package fr.valle.report_generator

import app.path.FilePath
import domain.docx.modifier.DocxArrayModifier
import domain.docx.reader.DocxReader
import domain.docx.writer.DocxWriter
import features.services.OpenDocxReport

import java.nio.file.Paths

object MainColumnWork extends App {
  val docxReader = DocxReader()
  val docxArrayModifier = DocxArrayModifier()
  val docxWriter = DocxWriter()
  val openDocxReport = OpenDocxReport()

  val pathRes = "src/test/resources"

  val updatedDoc = docxArrayModifier.addRow(document = docxReader.readDocx(templateFilePath = FilePath.stringToFilePath(Paths.get(
    pathRes, "template-test-add-column.docx").toString)))

  val writeResult = docxWriter.write(templateDoc = updatedDoc, FilePath.stringToFilePath(Paths.get(
    pathRes, "template-test-add-column-updated.docx").toString))

  println(writeResult)
  openDocxReport.open(fileLocation = writeResult.outputPath)
}

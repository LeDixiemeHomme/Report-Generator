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

  val updatedDoc = docxArrayModifier.addColumn(document = docxReader.readDocx(templateFilePath = FilePath.stringToFilePath(Paths.get("src/test/resources", "template-test-add-column.docx").toString)))
  val writeResult = docxWriter.write(templateDoc = updatedDoc, FilePath.stringToFilePath(Paths.get("src/test/resources", "template-test-add-column-updated.docx").toString))

  println(writeResult)
  openDocxReport.open(fileLocation = writeResult.outputPath)
}

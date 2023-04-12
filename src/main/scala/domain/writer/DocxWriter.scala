package fr.valle.report_generator
package domain.writer

import org.apache.poi.xwpf.usermodel.XWPFDocument

import java.io.FileOutputStream

class DocxWriter {
  def write(templateDoc: XWPFDocument, outputFilePath: String): String = {
    // Write the modified document to the output file
    val outputStream = new FileOutputStream(outputFilePath)
    try {
      templateDoc.write(outputStream)
      outputStream.close()
      s"Successfully written in $outputFilePath"
    }
  }

  def writeFromString(text: String, filePath: String): Unit = {
    val doc = new XWPFDocument()
    val para = doc.createParagraph()
    val run = para.createRun()
    run.setText(text)

    val out = new FileOutputStream(filePath)
    doc.write(out)
    out.close()

    println(s"Successfully wrote to $filePath")
  }
}

object DocxWriter {
  def apply(): DocxWriter = new DocxWriter()
}
package fr.valle.report_generator
package domain.writer

import org.apache.poi.xwpf.usermodel.XWPFDocument

import java.io.FileOutputStream

class DocxWriter {
  def write(templateDoc: XWPFDocument, outputDirPath: String, fileName: String): String = {
    // Write the modified document to the output file
    val outputStream = new FileOutputStream(outputDirPath + fileName + ".docx")
    try {
      templateDoc.write(outputStream)
      outputStream.close()
      s"Successfully written in $outputDirPath"
    }
  }
}

object DocxWriter {
  def apply(): DocxWriter = new DocxWriter()
}
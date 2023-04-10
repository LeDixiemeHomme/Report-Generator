package fr.valle.report_generator
package domain.reader

import org.apache.poi.xwpf.usermodel.XWPFDocument

import java.io.{File, FileInputStream}

class DocxReader {

  def readDocx(templateFilePath: String): XWPFDocument = {
    // Read the template file into a XWPFDocument object
    val templateFile: File = new File(templateFilePath)
    val templateStream: FileInputStream = new FileInputStream(templateFile)
    val templateDoc: XWPFDocument = new XWPFDocument(templateStream)

    templateDoc
  }
}

object DocxReader {
  def apply(): DocxReader = new DocxReader()
}
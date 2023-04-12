package fr.valle.report_generator
package domain.parser

import org.apache.poi.xwpf.usermodel.{XWPFDocument, XWPFParagraph}

import java.io.FileInputStream
import java.util
import scala.collection.convert.ImplicitConversions.`list asScalaBuffer`

class DocxParser {
  def parseDocx(filePath: String): String = {
    val document = new XWPFDocument(new FileInputStream(filePath))
    val paragraphs: util.List[XWPFParagraph] = document.getParagraphs
    val text = paragraphs.map(_.getText).mkString("\n")
    document.close()

    text
  }
}

object DocxParser {
  def apply(): DocxParser = new DocxParser()
}

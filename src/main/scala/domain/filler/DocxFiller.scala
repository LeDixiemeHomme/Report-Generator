package fr.valle.report_generator
package domain.filler

import org.apache.poi.xwpf.usermodel.{XWPFDocument, XWPFParagraph, XWPFRun}

import scala.jdk.CollectionConverters.CollectionHasAsScala

class DocxFiller {
  def fillDocx(templateDoc: XWPFDocument, valuesMap: Map[String, String]): XWPFDocument = {

    val filledTemplateDoc: XWPFDocument = templateDoc

    // Loop through all the paragraphs in the document and replace the values
    for (para: XWPFParagraph <- filledTemplateDoc.getParagraphs.asScala) {
      for (run: XWPFRun <- para.getRuns.asScala) {
        var text = run.getText(0)
        if (text != null) {
          // Replace all occurrences of the values with the corresponding values in the map
          for ((key, value) <- valuesMap) {
            text = text.replace(key, value)
          }
          // Set the new text in the run
          run.setText(text, 0)
        }
      }
    }

    filledTemplateDoc
  }
}

object DocxFiller {
  def apply(): DocxFiller = new DocxFiller()
}
package fr.valle.report_generator
package domain.docx.modifier

import org.apache.logging.log4j.scala.Logging
import org.apache.poi.xwpf.usermodel.{XWPFDocument, XWPFTable}

import scala.jdk.CollectionConverters.CollectionHasAsScala

class DocxArrayModifier extends Logging with IsADocxArrayModifierTrait {
  override def addRow(document: XWPFDocument): XWPFDocument = {
    for (table: XWPFTable <- document.getTables.asScala) {
      val rows = table.getRows.asScala
      table.addRow(rows.last)
    }
    document
  }
}

object DocxArrayModifier {
  def apply(): DocxArrayModifier = new DocxArrayModifier()
}

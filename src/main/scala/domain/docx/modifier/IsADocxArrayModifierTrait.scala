package fr.valle.report_generator
package domain.docx.modifier

import org.apache.poi.xwpf.usermodel.XWPFDocument

trait IsADocxArrayModifierTrait {
  def addRow(document: XWPFDocument): XWPFDocument
}

package fr.valle.report_generator
package domain.docx.modifier

import org.apache.poi.xwpf.usermodel.XWPFDocument

trait IsADocxArrayModifierTrait {
  def addColumn(document: XWPFDocument): XWPFDocument
}

package fr.valle.report_generator
package domain.docx.modifier

import org.apache.poi.xwpf.usermodel.{XWPFTable, XWPFTableRow}

trait IsADocxArrayModifierTrait {
  def duplicateLastRowOfTable(table: XWPFTable): XWPFTable

  def updateRow(row: XWPFTableRow, texts: List[String]): XWPFTableRow
}

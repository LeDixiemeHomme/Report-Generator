package fr.valle.report_generator
package domain.docx.modifier

import org.apache.logging.log4j.scala.Logging
import org.apache.poi.xwpf.usermodel.{XWPFDocument, XWPFTable, XWPFTableCell, XWPFTableRow}

import scala.jdk.CollectionConverters.CollectionHasAsScala

class DocxArrayModifier extends Logging with IsADocxArrayModifierTrait {
  override def addColumn(document: XWPFDocument): XWPFDocument = {

    for (table: XWPFTable <- document.getTables.asScala) {

      val newCell = table.getRows.get(0).addNewTableCell()
      table.addRow(table.getRows.get(0))
      newCell.setText("New cell content")

      for (row: XWPFTableRow <- table.getRows.asScala) {
        // Loop through the cells of the row
        for (cell: XWPFTableCell <- row.getTableCells.asScala) {
          val text = cell.getText
          if (text != null) {
            // Replace all occurrences of the values with the corresponding values in the map
            println(text)
            // Set the new text in the cell
            cell.getParagraphs.get(0).getRuns.get(0).setText(text, 0)
          }
        }
      }
    }
    document
  }
}

object DocxArrayModifier {
  def apply(): DocxArrayModifier = new DocxArrayModifier()
}

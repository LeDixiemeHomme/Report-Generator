package fr.valle.report_generator
package domain.docx.modifier

import org.apache.logging.log4j.scala.Logging
import org.apache.poi.xwpf.usermodel.{XWPFTable, XWPFTableRow}

import scala.collection.convert.ImplicitConversions.{`iterable AsScalaIterable`, `iterable asJava`}
import scala.jdk.CollectionConverters.ListHasAsScala

class DocxArrayModifier extends Logging with IsADocxArrayModifierTrait {
  override def duplicateLastRowOfTable(table: XWPFTable): XWPFTable = {
    val lastRow = table.getRows.asScala.toList.last
    table.addRow(lastRow)
    table
  }

  override def updateRow(row: XWPFTableRow, texts: List[String]): XWPFTableRow = {
    val cells = row.getTableCells
    cells.forEach(cell => {
      val paras = cell.getParagraphs
      paras.forEach(para => {
        val runs = para.getRuns
        runs.zipWithIndex.forEach { case (run, index) =>
          run.setText(f"benoit $index", 0)
        }
      })
    })
    row
  }

  override def addColumnToTable(table: XWPFTable): XWPFTable = {
    table.getRows.zipWithIndex.forEach { case (row, index) => addCellToRow(row = row, text = f"$index. value")}
    table
  }

  private def addCellToRow(row: XWPFTableRow, text: String): XWPFTableRow = {
    val cell = row.addNewTableCell()
    cell.setText(text)
    row
  }
}

object DocxArrayModifier {
  def apply(): DocxArrayModifier = new DocxArrayModifier()
}

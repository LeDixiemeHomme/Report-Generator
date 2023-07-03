package fr.valle.report_generator
package domain.docx.modifier

import org.apache.logging.log4j.scala.Logging
import org.apache.poi.xwpf.usermodel.{XWPFTable, XWPFTableRow}

import scala.jdk.CollectionConverters.ListHasAsScala

class DocxArrayModifier extends Logging with IsADocxArrayModifierTrait {
  override def duplicateLastRowOfTable(table: XWPFTable): XWPFTable = {
    table.addRow(table.getRows.asScala.toList.last)
    table
  }

  override def updateRow(row: XWPFTableRow, texts: List[String]): XWPFTableRow = {
    val cells = row.getTableCells
    cells.forEach(cell => {
      cell.setText("benoit")
      val paras = cell.getParagraphs
      paras.forEach(para => {
        val runs = para.getRuns
        runs.forEach(run => println("run => " + run.toString))
      })
    })
    row
  }
}

object DocxArrayModifier {
  def apply(): DocxArrayModifier = new DocxArrayModifier()
}

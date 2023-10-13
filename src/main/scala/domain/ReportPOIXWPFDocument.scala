package fr.valle.report_generator
package domain

import domain.docx.modifier.DocxArrayModifier

import org.apache.logging.log4j.scala.Logging
import org.apache.poi.xwpf.usermodel.{XWPFDocument, XWPFTable}

import scala.jdk.CollectionConverters.ListHasAsScala

class ReportPOIXWPFDocument(val document: XWPFDocument) extends Logging {

  private val docxArrayModifier = DocxArrayModifier()

  private def tables(): List[XWPFTable] = document.getTables.asScala.toList

  private def tableSorbonnes(): XWPFTable = tables().head

  def addRowToTableSorbonnes(): XWPFTable = {
    docxArrayModifier.duplicateLastRowOfTable(table = tableSorbonnes())
  }

  def addColumnToTableSorbonnes(): XWPFTable = {
    docxArrayModifier.addColumnToTable(table = tableSorbonnes())
  }
}

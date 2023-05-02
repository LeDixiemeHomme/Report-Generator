package fr.valle.report_generator
package UI.sections.pagesection.pages

import UI.DebugBorder

import scalafx.geometry.Pos
import scalafx.scene.control.Label
import scalafx.scene.layout._
import scalafx.scene.paint.Color

class OtherReportFormPage extends IsAPageTrait {
  private val body: VBox = new VBox {
    border = DebugBorder(Color.Purple).border
    alignment = Pos.Center
    id = myPageID
    children = Seq(
      new Label {
        text = "Cette page a pour but de montrer que la nav bar est fonctionnelle."
        style = "-fx-font-size: 20px"
        textFill = Color.Red
      }
    )
  }

  override def myPage: APage = APage(body = body)

  override def myPageID: String = OtherReportFormPage.OTHER_REPORT_FORM_PAGE_ID

  override def myPageName: String = "Autre rapport"
}

object OtherReportFormPage {
  def apply(): OtherReportFormPage = new OtherReportFormPage()

  final val OTHER_REPORT_FORM_PAGE_ID: String = "PageTwo"
}
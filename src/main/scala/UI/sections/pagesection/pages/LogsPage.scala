package fr.valle.report_generator
package UI.sections.pagesection.pages

import UI.DebugBorder

import scalafx.geometry.Pos
import scalafx.scene.control.Label
import scalafx.scene.layout._
import scalafx.scene.paint.Color

class LogsPage extends IsAPageTrait {
  private val body: VBox = new VBox {
    border = DebugBorder(Color.Purple).border
    alignment = Pos.Center
    id = LogsPage.PAGE_ID
    children = Seq {
      new Label {
        text = "Pas encore terminé"
        textFill = Color.Red
      }
    }
  }

  override def myPage: Page = Page(body = body)
}

object LogsPage {
  def apply(): LogsPage = new LogsPage()

  final val PAGE_ID: String = "PageTwo"
}
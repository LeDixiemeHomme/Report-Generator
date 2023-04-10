package fr.valle.report_generator
package UI.sections.pagesection.pages

import scalafx.geometry.Pos
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.VBox

class PageTwo extends IsAPageTrait {
  private val body: VBox = new VBox {
    alignment = Pos.Center
    id = "PageTwo"
    children = Seq {
      new Label("Stage two")
      new Button("Submit Stage two")
    }
  }

  override def myPage: Page = new Page(body = body)
}

object PageTwo {
  def apply(): PageTwo = new PageTwo()
}
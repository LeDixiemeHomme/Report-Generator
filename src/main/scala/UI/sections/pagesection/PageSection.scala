package fr.valle.report_generator
package UI.sections.pagesection

import UI.{DebugBorder, Shaper}
import UI.sections.pagesection.pages.IsAPageTrait

import scalafx.geometry.Pos
import scalafx.scene.layout._
import scalafx.scene.paint.Color

class PageSection(pageList: List[IsAPageTrait]) extends IsAPageSectionTrait {

  private val stackPane: StackPane = new StackPane {
    id = PageSection.STACK_PANE_ID
    children = pageList.map(_.myPage.myBody)
  }

  private val section: HBox = new HBox {
    border = DebugBorder(Color.White).border
    alignment = Pos.Center
    if(Shaper.smallHeightScreenMode) {
      prefHeight = 300
    } else {
      prefHeight = 450
    }
    children = stackPane
  }

  override def mySection: HBox = section

  override def allVisibleFalse(): Unit = {
    stackPane.children.foreach(_.setVisible(false))
  }

  override def myPages: List[IsAPageTrait] = pageList
}

object PageSection {
  def apply(pageList: List[IsAPageTrait]): PageSection = new PageSection(pageList)
  final val STACK_PANE_ID: String = "PageSectionStackPane"
}
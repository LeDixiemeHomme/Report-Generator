package fr.valle.report_generator
package UI.sections.pagesection

import UI.DebugBorder
import UI.sections.pagesection.pages.IsAPageTrait

import scalafx.geometry.Pos
import scalafx.scene.layout._
import scalafx.scene.paint.Color

class PageSection(stageList: List[IsAPageTrait]) extends IsAPageSectionTrait {

  private val stackPane: StackPane = new StackPane {
    id = PageSection.STACK_PANE_ID
    children = stageList.map(_.myPage.myBody)
  }

  private val section: HBox = new HBox {
    border = DebugBorder(Color.White).border
    alignment = Pos.Center
    prefHeight = 400
    children = stackPane
  }

  override def mySection: HBox = section

  override def allVisibleFalse(): Unit = {
    stackPane.children.foreach(_.setVisible(false))
  }
}

object PageSection {
  def apply(stageList: List[IsAPageTrait]): PageSection = new PageSection(stageList)
  final val STACK_PANE_ID: String = "PageSectionStackPane"
}
package fr.valle.report_generator
package UI.sections.pagesection

import UI.sections.pagesection.pages.IsAPageTrait

import scalafx.scene.layout.{HBox, StackPane}

class PageSection(stageList: List[IsAPageTrait]) extends IsAPageSectionTrait {
  private val stackPane: StackPane = new StackPane {
    id = "MainStackPane"
    children = stageList.map(_.myPage.myBody)
  }

  private val section: HBox = new HBox {
    children = stackPane
  }

  override def mySection: HBox = section

  override def allVisibleFalse(): Unit = {
    stackPane.children.foreach(_.setVisible(false))
  }
}

object PageSection {
  def apply(stageList: List[IsAPageTrait]): PageSection = new PageSection(stageList)
}
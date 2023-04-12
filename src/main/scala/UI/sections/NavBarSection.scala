package fr.valle.report_generator
package UI.sections

import UI.DebugBorder
import UI.sections.pagesection.statepattern.PageStateMachine
import UI.sections.pagesection.{IsAPageSectionTrait, PageSection}

import scalafx.geometry.Insets
import scalafx.scene.control.Button
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color

class NavBarSection(pageSection: IsAPageSectionTrait) extends IsASectionTrait {
  private val stateMachine = new PageStateMachine(pageSection = pageSection)

  private val pageOneButton: Button = new Button {
    text = "Page One"
    style = "-fx-background-color: transparent; -fx-text-fill: white;"
    onAction = _ => {
      stateMachine.input(PageStateMachine.GO_TO_PAGE_ONE)
    }
  }

  private val pageTwoButton: Button = new Button {
    text = "Page Two"
    style = "-fx-background-color: transparent; -fx-text-fill: white;"
    onAction = _ => {
      stateMachine.input(PageStateMachine.GO_TO_PAGE_TWO)
    }
  }

  private val section: HBox = new HBox {
    border = DebugBorder(Color.Orange).border
    spacing = 10
    padding = Insets(10, 20, 10, 20)
    style = "-fx-background-color: #333;"
    children = Seq(pageOneButton, pageTwoButton)
  }

  override def mySection: HBox = section
}

object NavBarSection {
  def apply(pageSection: IsAPageSectionTrait): NavBarSection = new NavBarSection(pageSection)
}

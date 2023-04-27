package fr.valle.report_generator
package UI.sections

import UI.DebugBorder
import UI.sections.pagesection.IsAPageSectionTrait
import UI.sections.pagesection.pages.pagestatepattern.PageStateMachine

import org.apache.logging.log4j.scala.Logging
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.Button
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color

class NavBarSection(pageSection: IsAPageSectionTrait) extends Logging with IsASectionTrait {
  private val stateMachine = PageStateMachine(pageSection = pageSection)

  private val pageNames: List[String] = pageSection.myPages.map(_.myPageName)

  private val pageOneButton: Button = new Button {
    text = pageNames.head
    style = "-fx-font-size: 20px; -fx-background-color: white; -fx-text-fill: black; -fx-border-color: white; -fx-border-width: 2px;"
    onAction = _ => {
      stateMachine.input(PageStateMachine.GO_TO_INTERVENTION_DATA_FORM_PAGE)
      style = "-fx-font-size: 20px; -fx-background-color: white; -fx-text-fill: black; -fx-border-color: white; -fx-border-width: 2px;"
      pageTwoButton.style = "-fx-font-size: 20px; -fx-background-color: transparent; -fx-text-fill: white;"
      pageThreeButton.style = "-fx-font-size: 20px; -fx-background-color: transparent; -fx-text-fill: white;"
    }
  }

  private val pageTwoButton: Button = new Button {
    text = pageNames(1)
    style = "-fx-font-size: 20px; -fx-background-color: transparent; -fx-text-fill: white;"
    onAction = _ => {
      stateMachine.input(PageStateMachine.GO_TO_REPORT_DATA_V1_FORM_PAGE)
      style = "-fx-font-size: 20px; -fx-background-color: white; -fx-text-fill: black; -fx-border-color: white; -fx-border-width: 2px;"
      pageOneButton.style = "-fx-font-size: 20px; -fx-background-color: transparent; -fx-text-fill: white;"
      pageThreeButton.style = "-fx-font-size: 20px; -fx-background-color: transparent; -fx-text-fill: white;"
    }
  }

  private val pageThreeButton: Button = new Button {
    text = pageNames(2)
    style = "-fx-font-size: 20px; -fx-background-color: transparent; -fx-text-fill: white;"
    onAction = _ => {
      stateMachine.input(PageStateMachine.GO_TO_LOGS_PAGE)
      style = "-fx-font-size: 20px; -fx-background-color: white; -fx-text-fill: black; -fx-border-color: white; -fx-border-width: 2px;"
      pageOneButton.style = "-fx-font-size: 20px; -fx-background-color: transparent; -fx-text-fill: white;"
      pageTwoButton.style = "-fx-font-size: 20px; -fx-background-color: transparent; -fx-text-fill: white;"
    }
  }

  private val section: HBox = new HBox {
    border = DebugBorder(Color.Orange).border
    spacing = 10
    alignment = Pos.Center
    padding = Insets(15, 0, 15, 0)
    margin = Insets(0, 100, 0, 100)
    style = "-fx-background-color: #333;"
    children = Seq(pageOneButton, pageTwoButton, pageThreeButton)
  }

  override def mySection: HBox = section
}

object NavBarSection {
  def apply(pageSection: IsAPageSectionTrait): NavBarSection = new NavBarSection(pageSection)
}

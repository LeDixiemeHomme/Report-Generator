package fr.valle.report_generator
package UI.sections.navbarsection

import UI.DebugBorder
import UI.sections.navbarsection.navbarcontent.navbarbutton.{IsANavBarButtonTrait, NavBarButton}
import UI.sections.pagesection.IsAPageSectionTrait
import UI.sections.pagesection.pages.pagestatepattern.PageStateMachine

import org.apache.logging.log4j.scala.Logging
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.Button
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color

class NavBarSection(pageSection: IsAPageSectionTrait) extends Logging with IsANavBarSectionTrait {
  private val pageStateMachine = PageStateMachine(pageSection = pageSection)

  private val pageNames: List[String] = pageSection.myPages.map(_.myPageName)


  private val selectedButtonStyle: String = "-fx-font-size: 20px; -fx-background-color: white; -fx-text-fill: black; -fx-border-color: white; -fx-border-width: 2px;"
  private val enteredSelectedButtonStyle: String = "-fx-font-size: 20px; -fx-background-color: white; -fx-text-fill: black; -fx-border-color: blue; -fx-border-width: 2px;"
  private val exitedSelectedButtonStyle: String = "-fx-font-size: 20px; -fx-background-color: white; -fx-text-fill: black; -fx-border-color: green; -fx-border-width: 2px;"
  private val unselectedButtonStyle: String = "-fx-font-size: 20px; -fx-background-color: transparent; -fx-text-fill: white;"
  private val enteredUnselectedButtonStyle: String = "-fx-font-size: 20px; -fx-background-color: transparent; -fx-text-fill: white; -fx-border-color: blue;"
  private val exitedUnselectedButtonStyle: String = "-fx-font-size: 20px; -fx-background-color: transparent; -fx-text-fill: white; -fx-border-color: green;"

  private val goToReportDataV1PageNavBarButton: IsANavBarButtonTrait = NavBarButton(textButton = pageNames.head, buttonStyle = selectedButtonStyle, PageStateMachine.GO_TO_REPORT_DATA_V1_FORM_PAGE)
  private val goToOtherReportFormPageNavBarButton: IsANavBarButtonTrait = NavBarButton(textButton = pageNames(1), buttonStyle = unselectedButtonStyle, PageStateMachine.GO_TO_LOGS_PAGE)

  private val goToReportDataV1PageButton: Button = goToReportDataV1PageNavBarButton.myButton
  private val goToOtherReportFormPageButton: Button = goToOtherReportFormPageNavBarButton.myButton

  goToReportDataV1PageButton.onAction = _ => {
    goToReportDataV1PageButton.style = selectedButtonStyle
    pageStateMachine.input(PageStateMachine.GO_TO_REPORT_DATA_V1_FORM_PAGE)
    goToOtherReportFormPageButton.style = unselectedButtonStyle
  }

  goToReportDataV1PageButton.onMouseEntered = _ => {
    goToReportDataV1PageButton.style = enteredSelectedButtonStyle
  }

  goToReportDataV1PageButton.onMouseExited = _ => {
    goToReportDataV1PageButton.style = exitedSelectedButtonStyle
  }

  goToOtherReportFormPageButton.onMouseEntered = _ => {
    goToOtherReportFormPageButton.style = enteredUnselectedButtonStyle
  }

  goToOtherReportFormPageButton.onMouseExited = _ => {
    goToOtherReportFormPageButton.style = exitedUnselectedButtonStyle
  }

  goToOtherReportFormPageButton.onAction = _ => {
    goToOtherReportFormPageButton.style = selectedButtonStyle
    pageStateMachine.input(PageStateMachine.GO_TO_LOGS_PAGE)
    goToReportDataV1PageButton.style = unselectedButtonStyle
  }

  private val section: HBox = new HBox {
    border = DebugBorder(Color.Orange).border
    spacing = 10
    alignment = Pos.Center
    padding = Insets(15, 0, 15, 0)
    margin = Insets(0, 100, 0, 100)
    style = "-fx-background-color: #333;"
    children = Seq(goToReportDataV1PageButton, goToOtherReportFormPageButton)
  }

  override def mySection: HBox = section
}

object NavBarSection {
  def apply(pageSection: IsAPageSectionTrait): NavBarSection = new NavBarSection(pageSection)
}

package fr.valle.report_generator
package UI.sections.navbarsection

import UI.DebugBorder
import UI.sections.navbarsection.navbarcontent.navbarbutton.NavBarButtonStyles.{selectedEnteredButtonStyle, unselectedEnteredButtonStyle, unselectedExitedButtonStyle}
import UI.sections.navbarsection.navbarcontent.navbarbutton.{IsANavBarButtonTrait, NavBarButton}
import UI.sections.pagesection.IsAPageSectionTrait
import UI.sections.pagesection.pages.pagestatepattern.PageStateMachine
import UI.sections.pagesection.pages.{OtherReportFormPage, ReportDataV1FormPage}

import org.apache.logging.log4j.scala.Logging
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.Button
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color

class NavBarSection(pageSection: IsAPageSectionTrait) extends Logging with IsANavBarSectionTrait {
  private val pageStateMachine = PageStateMachine(pageSection = pageSection)

  private val pageNames: List[String] = pageSection.myPages.map(_.myPageName)

  private val goToReportDataV1PageNavBarButton: IsANavBarButtonTrait = NavBarButton(
    textButton = pageNames.head,
    buttonStyle = selectedEnteredButtonStyle,
    PageStateMachine.GO_TO_REPORT_DATA_V1_FORM_PAGE
  )

  private val goToOtherReportFormPageNavBarButton: IsANavBarButtonTrait = NavBarButton(
    textButton = pageNames(1),
    buttonStyle = unselectedExitedButtonStyle,
    PageStateMachine.GO_TO_LOGS_PAGE
  )

  private val goToReportDataV1PageButton: Button = goToReportDataV1PageNavBarButton.myButton
  private val goToOtherReportFormPageButton: Button = goToOtherReportFormPageNavBarButton.myButton

  goToReportDataV1PageButton.onAction = _ => {
    goToReportDataV1PageButton.style = selectedEnteredButtonStyle
    pageStateMachine.input(PageStateMachine.GO_TO_REPORT_DATA_V1_FORM_PAGE)
    goToOtherReportFormPageButton.style = unselectedExitedButtonStyle
  }

  goToOtherReportFormPageButton.onAction = _ => {
    goToOtherReportFormPageButton.style = selectedEnteredButtonStyle
    pageStateMachine.input(PageStateMachine.GO_TO_LOGS_PAGE)
    goToReportDataV1PageButton.style = unselectedExitedButtonStyle
  }

  private def handleEnteredExitedState(pageState: PageStateMachine, button: Button, strState: String): Unit = {
    button.onMouseEntered = _ => {
      if (pageState.strState != strState) {
        button.style = unselectedEnteredButtonStyle
      }
    }
    button.onMouseExited = _ => {
      if (pageState.strState != strState) {
        button.style = unselectedExitedButtonStyle
      }
    }
  }

  handleEnteredExitedState(pageState = pageStateMachine, button = goToReportDataV1PageButton, strState = ReportDataV1FormPage.REPORT_DATA_V1_FORM_PAGE_ID)
  handleEnteredExitedState(pageState = pageStateMachine, button = goToOtherReportFormPageButton, strState = OtherReportFormPage.OTHER_REPORT_FORM_PAGE_ID)

  private val section: HBox = new HBox {
    border = DebugBorder(Color.Orange).border
    spacing = 10
    alignment = Pos.Center
    padding = Insets(15, 0, 15, 0)
    margin = Insets(0, 200, 0, 200)
    style = "-fx-background-color: #333; -fx-background-radius: 20;"
    children = Seq(goToReportDataV1PageButton, goToOtherReportFormPageButton)
  }

  override def mySection: HBox = section
}

object NavBarSection {
  def apply(pageSection: IsAPageSectionTrait): NavBarSection = new NavBarSection(pageSection)
}

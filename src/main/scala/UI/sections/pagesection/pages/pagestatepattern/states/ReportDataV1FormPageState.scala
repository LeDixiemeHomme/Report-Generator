package fr.valle.report_generator
package UI.sections.pagesection.pages.pagestatepattern.states

import UI.sections.pagesection.pages.ReportDataV1FormPage
import UI.sections.pagesection.pages.pagestatepattern.{PageStateMachine, PageStateTrait}
import UI.sections.pagesection.{IsAPageSectionTrait, PageSection}
import logging.LogsKeeper

import org.apache.logging.log4j.scala.Logging

class ReportDataV1FormPageState(pageSection: IsAPageSectionTrait) extends Logging with PageStateTrait {
  pageSection.allVisibleFalse()
  private val pageBody = pageSection.mySection.lookup("#" + PageSection.STACK_PANE_ID).lookup("#" + PageStateMachine.GO_TO_REPORT_DATA_V1_FORM_PAGE)
  pageBody.visible = true

  override def handle(input: String): PageStateTrait = input match {
    case PageStateMachine.GO_TO_LOGS_PAGE => LogsPageState(pageSection = pageSection)
    case _ => LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.INFO, "Already on page " + ReportDataV1FormPage().myPageName, classFrom = getClass); this
  }
}

object ReportDataV1FormPageState {
  def apply(pageSection: IsAPageSectionTrait): ReportDataV1FormPageState = new ReportDataV1FormPageState(pageSection)
}
package fr.valle.report_generator
package UI.sections.pagesection.pages.pagestatepattern.states

import UI.sections.pagesection.pages.ReceptionReportFormPage
import UI.sections.pagesection.pages.pagestatepattern.{PageStateMachine, PageStateTrait}
import UI.sections.pagesection.{IsAPageSectionTrait, PageSection}
import logging.LogsKeeper

import org.apache.logging.log4j.scala.Logging

class ReceptionReportDataFormPageState(pageSection: IsAPageSectionTrait) extends Logging with PageStateTrait {
  pageSection.allVisibleFalse()
  private val pageBody = pageSection.mySection.lookup("#" + PageSection.STACK_PANE_ID).lookup("#" + PageStateMachine.GO_TO_RECEPTION_REPORT_DATA_FORM_PAGE)
  pageBody.visible = true

  override def handle(input: String): PageStateTrait = input match {
    case PageStateMachine.GO_TO_LOGS_PAGE => LogsPageState(pageSection = pageSection)
    case _ => LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.INFO, "Already on page " + ReceptionReportFormPage().myPageName, classFrom = getClass); this
  }
}

object ReceptionReportDataFormPageState {
  def apply(pageSection: IsAPageSectionTrait): ReceptionReportDataFormPageState = new ReceptionReportDataFormPageState(pageSection)
}
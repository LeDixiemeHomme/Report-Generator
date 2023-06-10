package fr.valle.report_generator
package UI.sections.pagesection.pages.pagestatepattern.states

import UI.sections.pagesection.pages.OtherReportFormPage
import UI.sections.pagesection.pages.pagestatepattern.{PageStateMachine, PageStateTrait}
import UI.sections.pagesection.{IsAPageSectionTrait, PageSection}
import logging.{Levels, Log, LogsKeeper}

import org.apache.logging.log4j.scala.Logging

class LogsPageState(pageSection: IsAPageSectionTrait) extends Logging with PageStateTrait {
  pageSection.allVisibleFalse()
  private val pageBody = pageSection.mySection.lookup("#" + PageSection.STACK_PANE_ID).lookup("#" + PageStateMachine.GO_TO_OTHER_REPORT_PAGE)
  pageBody.visible = true

  override def handle(input: String): PageStateTrait = input match {
    case PageStateMachine.GO_TO_RECEPTION_REPORT_DATA_FORM_PAGE => ReceptionReportDataFormPageState(pageSection = pageSection)
    case _ => LogsKeeper.keepAndLog(extLogger = logger, log = Log(message = s"Already on page ${OtherReportFormPage().myPageName}",
      level = Levels.INFO), classFrom = getClass); this
  }
}

object LogsPageState {
  def apply(pageSection: IsAPageSectionTrait): LogsPageState = new LogsPageState(pageSection)
}

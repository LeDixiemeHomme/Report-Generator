package fr.valle.report_generator
package UI.sections.pagesection.pages.pagestatepattern.states

import UI.sections.pagesection.pages.pagestatepattern.{PageStateMachine, PageStateTrait}
import UI.sections.pagesection.{IsAPageSectionTrait, PageSection}

class LogsPageState(pageSection: IsAPageSectionTrait) extends PageStateTrait {
  pageSection.allVisibleFalse()
  private val pageBody = pageSection.mySection.lookup("#" + PageSection.STACK_PANE_ID).lookup("#" + PageStateMachine.GO_TO_LOGS_PAGE)
  pageBody.visible = true

  override def handle(input: String): PageStateTrait = input match {
    case PageStateMachine.GO_TO_INTERVENTION_DATA_FORM_PAGE => InterventionDataFormPageState(pageSection = pageSection)
    case _ => println("Invalid input!"); this
  }
}

object LogsPageState {
  def apply(pageSection: IsAPageSectionTrait): LogsPageState = new LogsPageState(pageSection)
}

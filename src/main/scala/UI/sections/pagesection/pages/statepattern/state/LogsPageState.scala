package fr.valle.report_generator
package UI.sections.pagesection.pages.statepattern.state

import UI.sections.pagesection.IsAPageSectionTrait
import UI.sections.pagesection.pages.statepattern.{PageStateMachine, PageStateTrait}

class LogsPageState(pageSection: IsAPageSectionTrait) extends PageStateTrait {
  pageSection.allVisibleFalse()
  private val pageBody = pageSection.mySection.lookup("#MainStackPane").lookup("#" + PageStateMachine.GO_TO_PAGE_TWO)
  pageBody.visible = true

  override def handle(input: String): PageStateTrait = input match {
    case PageStateMachine.GO_TO_PAGE_ONE => InterventionDataFormPageState(pageSection = pageSection)
    case _ => println("Invalid input!"); this
  }
}

object LogsPageState {
  def apply(pageSection: IsAPageSectionTrait): LogsPageState = new LogsPageState(pageSection)
}

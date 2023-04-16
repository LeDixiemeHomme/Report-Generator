package fr.valle.report_generator
package UI.sections.pagesection.pages.pagestatepattern.states

import UI.sections.pagesection.pages.pagestatepattern.{PageStateMachine, PageStateTrait}
import UI.sections.pagesection.{IsAPageSectionTrait, PageSection}

class InterventionDataFormPageState(pageSection: IsAPageSectionTrait) extends PageStateTrait {
  pageSection.allVisibleFalse()
  private val pageBody = pageSection.mySection.lookup("#" + PageSection.STACK_PANE_ID).lookup("#" + PageStateMachine.GO_TO_PAGE_ONE)
  pageBody.visible = true

  override def handle(input: String): PageStateTrait = input match {
    case PageStateMachine.GO_TO_PAGE_TWO => LogsPageState(pageSection = pageSection)
    case _ => println("Invalid input!"); this
  }
}

object InterventionDataFormPageState {
  def apply(pageSection: IsAPageSectionTrait): InterventionDataFormPageState = new InterventionDataFormPageState(pageSection)
}
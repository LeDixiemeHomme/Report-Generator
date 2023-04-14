package fr.valle.report_generator
package UI.sections.pagesection.pages.statepattern

import UI.sections.pagesection.IsAPageSectionTrait
import UI.sections.pagesection.pages.statepattern.state.InterventionDataFormPageState

class PageStateMachine(pageSection: IsAPageSectionTrait) {
  private var state: PageStateTrait = new InterventionDataFormPageState(pageSection = pageSection)

  def input(input: String): Unit = {
    state = state.handle(input)
  }
}

object PageStateMachine {
  def apply(pageSection: IsAPageSectionTrait): PageStateMachine = new PageStateMachine(pageSection)

  val GO_TO_PAGE_ONE: String = "PageOne"
  val GO_TO_PAGE_TWO: String = "PageTwo"
}

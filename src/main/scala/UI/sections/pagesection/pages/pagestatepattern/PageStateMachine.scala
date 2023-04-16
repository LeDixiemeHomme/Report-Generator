package fr.valle.report_generator
package UI.sections.pagesection.pages.pagestatepattern

import UI.sections.pagesection.IsAPageSectionTrait
import UI.sections.pagesection.pages.pagestatepattern.states.InterventionDataFormPageState
import UI.sections.pagesection.pages.{InterventionDataFormPage, LogsPage}

class PageStateMachine(pageSection: IsAPageSectionTrait) {
  private var state: PageStateTrait = new InterventionDataFormPageState(pageSection = pageSection)

  def input(input: String): Unit = {
    state = state.handle(input)
  }
}

object PageStateMachine {
  def apply(pageSection: IsAPageSectionTrait): PageStateMachine = new PageStateMachine(pageSection)
  final val GO_TO_PAGE_ONE: String = InterventionDataFormPage.PAGE_ID
  final val GO_TO_PAGE_TWO: String = LogsPage.PAGE_ID
}

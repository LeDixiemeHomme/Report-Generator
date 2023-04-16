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
  final val GO_TO_INTERVENTION_DATA_FORM_PAGE: String = InterventionDataFormPage.INTERVENTION_DATA_FORM_PAGE_ID
  final val GO_TO_LOGS_PAGE: String = LogsPage.LOGS_PAGE_ID
}

package fr.valle.report_generator
package UI.sections.pagesection.pages.pagestatepattern

import UI.sections.pagesection.IsAPageSectionTrait
import UI.sections.pagesection.pages.pagestatepattern.states.ReportDataV1FormPageState
import UI.sections.pagesection.pages.{LogsPage, ReportDataV1FormPage}

class PageStateMachine(pageSection: IsAPageSectionTrait) {
  private var state: PageStateTrait = new ReportDataV1FormPageState(pageSection = pageSection)

  def input(input: String): Unit = {
    state = state.handle(input)
  }
}

object PageStateMachine {
  def apply(pageSection: IsAPageSectionTrait): PageStateMachine = new PageStateMachine(pageSection)
  final val GO_TO_LOGS_PAGE: String = LogsPage.LOGS_PAGE_ID
  final val GO_TO_REPORT_DATA_V1_FORM_PAGE: String = ReportDataV1FormPage.REPORT_DATA_V1_FORM_PAGE_ID
}

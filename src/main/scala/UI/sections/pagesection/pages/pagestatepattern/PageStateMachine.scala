package fr.valle.report_generator
package UI.sections.pagesection.pages.pagestatepattern

import UI.sections.pagesection.IsAPageSectionTrait
import UI.sections.pagesection.pages.pagestatepattern.states.ReceptionReportDataFormPageState
import UI.sections.pagesection.pages.{OtherReportFormPage, ReceptionReportFormPage}

class PageStateMachine(pageSection: IsAPageSectionTrait) {

  final val mySection: IsAPageSectionTrait = pageSection

  private var state: PageStateTrait = new ReceptionReportDataFormPageState(pageSection = mySection)

  var strState: String = ReceptionReportFormPage.RECEPTION_REPORT_DATA_FORM_PAGE_ID

  def input(input: String): Unit = {
    state = state.handle(input)
    strState = input
  }
}

object PageStateMachine {
  def apply(pageSection: IsAPageSectionTrait): PageStateMachine = new PageStateMachine(pageSection)
  final val GO_TO_RECEPTION_REPORT_DATA_FORM_PAGE: String = ReceptionReportFormPage.RECEPTION_REPORT_DATA_FORM_PAGE_ID
  final val GO_TO_LOGS_PAGE: String = OtherReportFormPage.OTHER_REPORT_FORM_PAGE_ID
}

package fr.valle.report_generator
package UI.sections.pagesection.statepattern.state

import UI.sections.pagesection.IsAPageSectionTrait
import UI.sections.pagesection.statepattern.{PageStateMachine, PageStateTrait}

class PageTwoState(pageSection: IsAPageSectionTrait) extends PageStateTrait {
  pageSection.allVisibleFalse()
  private val pageBody = pageSection.mySection.lookup("#MainStackPane").lookup("#" + PageStateMachine.GO_TO_PAGE_TWO)
  pageBody.visible = true

  override def handle(input: String): PageStateTrait = input match {
    case PageStateMachine.GO_TO_PAGE_ONE => PageOneState(pageSection = pageSection)
    case _ => println("Invalid input!"); this
  }
}

object PageTwoState {
  def apply(pageSection: IsAPageSectionTrait): PageTwoState = new PageTwoState(pageSection)
}

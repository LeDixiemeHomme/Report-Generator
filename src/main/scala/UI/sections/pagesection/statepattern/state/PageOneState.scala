package fr.valle.report_generator
package UI.sections.pagesection.statepattern.state

import UI.sections.pagesection.IsAPageSectionTrait
import UI.sections.pagesection.statepattern.{PageStateMachine, PageStateTrait}

class PageOneState(pageSection: IsAPageSectionTrait) extends PageStateTrait {
  pageSection.allVisibleFalse()
  private val pageBody = pageSection.mySection.lookup("#MainStackPane").lookup("#" + PageStateMachine.GO_TO_PAGE_ONE)
  pageBody.visible = true

  override def handle(input: String): PageStateTrait = input match {
    case PageStateMachine.GO_TO_PAGE_TWO => PageTwoState(pageSection = pageSection)
    case _ => println("Invalid input!"); this
  }
}

object PageOneState {
  def apply(pageSection: IsAPageSectionTrait): PageOneState = new PageOneState(pageSection)
}
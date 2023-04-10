package fr.valle.report_generator
package UI.sections.pagesection.statepattern

import UI.sections.pagesection.IsAPageSectionTrait

object PageStateMachine {
  def apply(pageSection: IsAPageSectionTrait): PageStateMachine = new PageStateMachine(pageSection)

  val GO_TO_PAGE_ONE: String = "PageOne"
  val GO_TO_PAGE_TWO: String = "PageTwo"
}

class PageOneState(pageSection: IsAPageSectionTrait) extends PageStateTrait {
  pageSection.allVisibleFalse()
  private val pageBody = pageSection.mySection.lookup("#MainStackPane").lookup("#" + PageStateMachine.GO_TO_PAGE_ONE)
  pageBody.visible = true

  override def handle(input: String): PageStateTrait = input match {
    case PageStateMachine.GO_TO_PAGE_TWO => new PageTwoState(pageSection = pageSection)
    case _ => println("Invalid input!"); this
  }
}

class PageTwoState(pageSection: IsAPageSectionTrait) extends PageStateTrait {
  pageSection.allVisibleFalse()
  private val pageBody = pageSection.mySection.lookup("#MainStackPane").lookup("#" + PageStateMachine.GO_TO_PAGE_TWO)
  pageBody.visible = true

  override def handle(input: String): PageStateTrait = input match {
    case PageStateMachine.GO_TO_PAGE_ONE => new PageOneState(pageSection = pageSection)
    case _ => println("Invalid input!"); this
  }
}

class PageStateMachine(pageSection: IsAPageSectionTrait) {
  private var state: PageStateTrait = new PageOneState(pageSection = pageSection)

  def input(input: String): Unit = {
    state = state.handle(input)
  }
}

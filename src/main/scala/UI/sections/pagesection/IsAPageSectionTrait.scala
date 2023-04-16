package fr.valle.report_generator
package UI.sections.pagesection

import UI.sections.IsASectionTrait
import UI.sections.pagesection.pages.IsAPageTrait

trait IsAPageSectionTrait extends IsASectionTrait {
  def myPages: List[IsAPageTrait]
  def allVisibleFalse(): Unit
}

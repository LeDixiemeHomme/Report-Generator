package fr.valle.report_generator
package UI.sections.pagesection.pages.statepattern

trait PageStateTrait {
  def handle(input: String): PageStateTrait
}

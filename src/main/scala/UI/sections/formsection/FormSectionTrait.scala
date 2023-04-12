package fr.valle.report_generator
package UI.sections.formsection

import scalafx.scene.control.TextField
import scalafx.scene.layout.HBox

trait FormSectionTrait {
  def myFormSection: HBox
  def myTextField: TextField
}

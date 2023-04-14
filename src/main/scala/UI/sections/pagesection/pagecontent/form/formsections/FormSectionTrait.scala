package fr.valle.report_generator
package UI.sections.pagesection.pagecontent.form.formsections

import scalafx.scene.control.TextField
import scalafx.scene.layout.HBox

trait FormSectionTrait {
  def myFormSection: HBox
  def myTextField: TextField
  def isRequired: Boolean
}

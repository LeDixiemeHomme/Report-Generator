package fr.valle.report_generator
package UI.sections.pagesection.pagecontent.form.formsections

import UI.DebugBorder

import scalafx.geometry.Pos
import scalafx.scene.control.{Label, TextField}
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color

class LabelTextFieldFormSection(label: String, val myTextField: TextField, required: Boolean) extends FormSectionTrait {
  myTextField.prefWidth = 200

  private val myLabel = new Label {
    text = label
    prefWidth = 110
    alignment = Pos.CenterLeft
    style = "-fx-text-fill: white;"
  }
  override def myFormSection: HBox = new HBox {
    border = DebugBorder(Color.Red).border
    prefWidth = 400
    spacing = 20
    children = Seq(myLabel, myTextField)
  }

  override def isRequired: Boolean = required
}

object LabelTextFieldFormSection {
  def apply(label: String, myTextField: TextField, required: Boolean): LabelTextFieldFormSection = new LabelTextFieldFormSection(label, myTextField, required)
}
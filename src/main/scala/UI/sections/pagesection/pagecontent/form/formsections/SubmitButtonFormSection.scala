package fr.valle.report_generator
package UI.sections.pagesection.pagecontent.form.formsections

import scalafx.geometry.Pos
import scalafx.scene.control.Button

class SubmitButtonFormSection() {
  //todo rework the button, text and position
  val myButton: Button = new Button {
    alignment = Pos.Center
    text = "Click me"
    disable = true
  }
}

object SubmitButtonFormSection {
}
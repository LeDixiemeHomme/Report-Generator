package fr.valle.report_generator
package UI.sections.pagesection.pagecontent.form.formsections

import UI.DebugBorder

import scalafx.geometry.Pos
import scalafx.scene.control.Button
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color

class SubmitButtonFormSection() {
  //todo rework the button, text and position

  val myButton: Button = new Button {
    text = "Créer le rapport"
    style = "-fx-font-size: 20px;"
    disable = true
    prefHeight = 50
    prefWidth = 200
  }

  val myButtonSection: HBox = new HBox {
    border = DebugBorder(Color.Yellow).border
    alignment = Pos.Center
    children = Seq(myButton)
  }
}

object SubmitButtonFormSection {
}

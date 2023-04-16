package fr.valle.report_generator
package UI.sections.pagesection.pagecontent.form.formsections

import UI.DebugBorder

import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.VBox
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

  val myButtonSection: VBox = new VBox {
    border = DebugBorder(Color.Yellow).border
    alignment = Pos.Center
    children = Seq(myButton, new Label {
      padding = Insets(5,0,0,0)
      text = "(*) champs obligatoires"
      style = "-fx-font-size: 15px; -fx-text-fill: white;"
    })
  }
}

object SubmitButtonFormSection {
}

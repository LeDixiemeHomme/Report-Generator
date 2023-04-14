package fr.valle.report_generator
package UI.sections.pagesection.pagecontent.form.formsections

import MainReportGeneratorJFXApp.stage
import UI.DebugBorder

import scalafx.geometry.Pos
import scalafx.scene.control.{Button, Label, TextField}
import scalafx.scene.layout._
import scalafx.scene.paint.Color
import scalafx.stage.FileChooser

class LabelTextFieldBrowseFormSection(label: String, val myTextField: TextField, required: Boolean) extends FormSectionTrait {
  myTextField.prefWidth = 200

  private val myLabel = new Label {
    text = label
    prefWidth = 110
    alignment = Pos.CenterLeft
    style = "-fx-text-fill: white;"
  }

  private val browseButton = new Button {
    text = "Browse"
    onAction = _ => {
      val fileChooser = new FileChooser()
      val selectedFile = fileChooser.showOpenDialog(stage)
      if (selectedFile != null) {
        myTextField.text = selectedFile.getPath
      }
    }
  }

  val myFormSection: HBox = new HBox {
    border = DebugBorder(Color.Red).border
    prefWidth = 400
    spacing = 20
    children = Seq(myLabel, myTextField, browseButton)
  }

  override def isRequired: Boolean = required
}

object LabelTextFieldBrowseFormSection {
  def apply(label: String, myTextField: TextField, required: Boolean): LabelTextFieldBrowseFormSection = new LabelTextFieldBrowseFormSection(label, myTextField, required)
}

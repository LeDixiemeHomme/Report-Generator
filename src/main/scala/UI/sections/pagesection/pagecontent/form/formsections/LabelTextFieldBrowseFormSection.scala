package fr.valle.report_generator
package UI.sections.pagesection.pagecontent.form.formsections

import MainReportGeneratorJFXApp.stage
import UI.DebugBorder
import UI.sections.pagesection.pagecontent.form.formsections.browsebuttonstrategypattern.BrowseButtonStrategyTrait

import scalafx.geometry.Pos
import scalafx.scene.control.{Label, TextField}
import scalafx.scene.layout._
import scalafx.scene.paint.Color

class LabelTextFieldBrowseFormSection(label: String, val myTextField: TextField, required: Boolean, browseStrategy: BrowseButtonStrategyTrait) extends FormSectionTrait {
  myTextField.prefWidth = 200

  private val myLabel = new Label {
    text = label
    prefWidth = 110
    alignment = Pos.CenterLeft
    style = "-fx-text-fill: white;"
  }

  private val browseButton = browseStrategy.browseButton(myTextField, stage)

  val myFormSection: HBox = new HBox {
    border = DebugBorder(Color.Red).border
    prefWidth = 400
    spacing = 20
    children = Seq(myLabel, myTextField, browseButton)
  }

  override def isRequired: Boolean = required
}

object LabelTextFieldBrowseFormSection {
  def apply(label: String, myTextField: TextField, required: Boolean, browseStrategy: BrowseButtonStrategyTrait): LabelTextFieldBrowseFormSection = new LabelTextFieldBrowseFormSection(label, myTextField, required, browseStrategy)
}

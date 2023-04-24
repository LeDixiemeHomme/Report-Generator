package fr.valle.report_generator
package UI.sections.pagesection.pagecontent.form.formsections

import MainReportGeneratorJFXApp.stage
import UI.DebugBorder
import UI.sections.pagesection.pagecontent.form.formsections.browsebuttonstrategypattern.BrowseButtonStrategyTrait

import scalafx.geometry.Pos
import scalafx.scene.control.{Label, TextField}
import scalafx.scene.layout._
import scalafx.scene.paint.Color

class LabelTextFieldBrowseFormSection(label: String, example: String, required: Boolean, browseStrategy: BrowseButtonStrategyTrait) extends FormSectionTrait {

  private val textField: TextField = new TextField {
    prefWidth = 490
    promptText = example
  }

  private val requiredString = if (required) " (*)" else ""

  private val finalLabel: String = label + requiredString

  private val myLabel = new Label {
    text = finalLabel
    prefWidth = 220
    alignment = Pos.CenterLeft
    style = "-fx-font-size: 16px; -fx-text-fill: white;"
  }

  private val browseButton = browseStrategy.optionalBrowseButton(textField, stage).getOrElse(new HBox())

  val myFormSection: HBox = new HBox {
    border = DebugBorder(Color.Red).border
    prefWidth = 400
    spacing = 20
    children = Seq(myLabel, myTextField, browseButton)
  }

  def myTextField: TextField = textField

  override def isRequired: Boolean = required
}

object LabelTextFieldBrowseFormSection {
  def apply(label: String, example: String, required: Boolean, browseStrategy: BrowseButtonStrategyTrait): LabelTextFieldBrowseFormSection =
    new LabelTextFieldBrowseFormSection(label, example, required, browseStrategy)
}

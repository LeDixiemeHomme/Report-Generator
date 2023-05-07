package fr.valle.report_generator
package UI.sections.pagesection.pagecontent.form.formsections

import MainReportGeneratorJFXApp.stage
import UI.DebugBorder
import UI.sections.pagesection.pagecontent.form.formsections.browsebuttonstrategypattern.BrowseButtonStrategyTrait

import scalafx.geometry.Pos
import scalafx.scene.control.{Label, TextField}
import scalafx.scene.layout._
import scalafx.scene.paint.Color

class LabelTextFieldBrowseFormSection(finalLabel: String, example: String, required: Boolean,
                                                browseStrategy: BrowseButtonStrategyTrait) extends IsAFormSectionTrait {

  private val myLabel = new HBox {
    alignment = Pos.CenterLeft
    children = new Label {
      text = finalLabel
      prefWidth = 235
      style = "-fx-font-size: 16px; -fx-text-fill: white;"
    }
  }

  private val textField: TextField = new TextField {
    prefWidth = 700
    prefHeight = 30
    promptText = example
    style = "-fx-font-size: 18px;"
  }

  private val browseButton = browseStrategy.optionalBrowseButton(textField, stage).getOrElse(new HBox())

  private val buttonHBox: HBox = new HBox {
    border = DebugBorder(Color.Red).border
    alignment = Pos.Center
    children = browseButton
    prefWidth = 200
  }

  val myFormSection: HBox = new HBox {
    border = DebugBorder(Color.Red).border
    prefWidth = 400
    spacing = 20
    children = Seq(myLabel, textField, buttonHBox)
  }

  def myTextField: TextField = textField

  override def isRequired: Boolean = required
}

object LabelTextFieldBrowseFormSection {
  def apply(label: String, example: String, required: Boolean, browseStrategy: BrowseButtonStrategyTrait): LabelTextFieldBrowseFormSection = {

    val requiredString = if (required) " (*)" else ""

    val finalLabel: String = label + requiredString

    new LabelTextFieldBrowseFormSection(finalLabel, example, required, browseStrategy)
  }
}

package fr.valle.report_generator
package UI.sections.pagesection.pagecontent.form

import UI.DebugBorder
import UI.sections.pagesection.pagecontent.form.formsections.{FormSectionTrait, SubmitButtonFormSection}

import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.TextField
import scalafx.scene.layout._
import scalafx.scene.paint.Color

class FormReport(forms: List[FormSectionTrait], submitButton: SubmitButtonFormSection) extends IsAFormTrait {
  // Add a listener to the fields to enable/disable the submit button
  val fields: Seq[TextField] = forms.filter(_.isRequired).map(_.myTextField)
  fields.foreach { field =>
    field.text.onChange { (_, _, _) =>
      submitButton.myButton.disable = fields.exists(_.text.value.isEmpty)
    }
  }

  private val formFields: VBox = new VBox {
    border = DebugBorder(Color.Blue).border
    alignment = Pos.Center
    spacing = 20
    prefWidth = 500
    padding = Insets(20)
    children = forms.map(_.myFormSection)
  }

  val myForm: VBox = new VBox {
    border = DebugBorder(Color.Green).border
    id = "PageOne"
    spacing = 20
    padding = Insets(20)
    hgrow = Priority.Always
    children = Seq(formFields, submitButton.myButton)
  }

}

object FormReport {
  def apply(forms: List[FormSectionTrait],
            submitButton: SubmitButtonFormSection
           ): FormReport =
    new FormReport(forms, submitButton)
}
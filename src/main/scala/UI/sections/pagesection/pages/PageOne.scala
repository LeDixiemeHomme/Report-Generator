package fr.valle.report_generator
package UI.sections.pagesection.pages

import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.{Button, Label, TextField}
import scalafx.scene.layout.{HBox, VBox}

class PageOne extends IsAPageTrait {

  val firstNameField = new TextField()
  val lastNameField = new TextField()
  val emailField = new TextField()

  val submitButton = new Button("Submit")
  submitButton.disable = true // disable by default

  // Add a listener to the fields to enable/disable the submit button
  val fields: Seq[TextField] = Seq(firstNameField, lastNameField, emailField)
  fields.foreach { field =>
    field.text.onChange { (_, _, newValue) =>
      submitButton.disable = fields.exists(_.text.value.isEmpty)
    }
  }

  val nameLabel = new Label("First Name:")
  nameLabel.prefWidth = 100
  nameLabel.alignment = Pos.BaselineRight

  val lastNameLabel = new Label("Last Name:")
  lastNameLabel.prefWidth = 100
  lastNameLabel.alignment = Pos.BaselineRight

  val emailLabel = new Label("Email:")
  emailLabel.prefWidth = 100
  emailLabel.alignment = Pos.BaselineRight

  val hbox1 = new HBox(10)
  hbox1.children.addAll(nameLabel, firstNameField)

  val hbox2 = new HBox(10)
  hbox2.children.addAll(lastNameLabel, lastNameField)

  val hbox3 = new HBox(10)
  hbox3.children.addAll(emailLabel, emailField)

  private val body: VBox = new VBox {
    id = "PageOne"
    alignment = Pos.Center
  }
  body.spacing = 20
  body.padding = Insets(20)
  body.children.addAll(hbox1, hbox2, hbox3, submitButton)

  override def myPage: Page = new Page(body = body)
}

object PageOne {
  def apply(): PageOne = new PageOne()
}

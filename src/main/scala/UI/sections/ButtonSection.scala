package fr.valle.report_generator
package UI.sections

import services.filling.{FillingDocxToDocxService, FillingResult, FillingServiceTrait}

import scalafx.scene.control.Button
import scalafx.scene.layout.HBox

class ButtonSection extends IsASectionTrait {

  private val fillingService: FillingServiceTrait = new FillingDocxToDocxService()

  private val button: Button = new Button {
    text = "Click me"
    onAction = _ => {
      // Define the template file path and output file path
      val templateFilePath = "./src/main/resources/inputs/FYC_scala.docx"
      val outputFilePath = "./outputs/result.docx"

      // Define the map of values to replace in the template
      val valuesMap = Map(
        "<Name>" -> "John Doe",
        "<Age>" -> "30",
        "<City>" -> "New York"
      )

      val result: FillingResult = fillingService.fill(templateFilePath = templateFilePath, valuesMap = valuesMap, outputFilePath = outputFilePath)

      println(result.toString)
    }
  }

  private val section: HBox = new HBox {
    children = Seq(button)
  }

  override def mySection: HBox = section
}

object ButtonSection {
  def apply(): ButtonSection = new ButtonSection()
}
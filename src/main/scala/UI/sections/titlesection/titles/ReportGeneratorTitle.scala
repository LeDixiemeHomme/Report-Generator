package fr.valle.report_generator
package UI.sections.titlesection.titles

import scalafx.scene.effect.DropShadow
import scalafx.scene.paint.Color.{DarkGray, White}
import scalafx.scene.paint.{LinearGradient, Stops}
import scalafx.scene.text.Text

class ReportGeneratorTitle() extends IsATitleTrait {

  private val words: List[String] = "Report" :: "Generator" :: Nil

  private def wordToDarkGrayText(word: String): Text = {
    new Text {
      text = word
      style = "-fx-font: bold 50pt sans-serif"
      fill = new LinearGradient(
        endX = 0,
        stops = Stops(White, DarkGray)
      )
    }
  }

  private val titleTextReport: Text = wordToDarkGrayText(word = words.head)
  private val titleTextGenerator: Text = wordToDarkGrayText(word = words(1))

  override def myWords: List[Text] = List(titleTextReport, titleTextGenerator)

  override def toTitle: String = {
    words.mkString("")
  }
}

object ReportGeneratorTitle {
  def apply(): ReportGeneratorTitle = new ReportGeneratorTitle()
}

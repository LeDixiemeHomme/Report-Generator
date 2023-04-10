package fr.valle.report_generator
package UI.main

import scalafx.scene.effect.DropShadow
import scalafx.scene.paint.Color.{DarkGray, DarkRed, Red, White}
import scalafx.scene.paint.{LinearGradient, Stops}
import scalafx.scene.text.Text

class MainTitle(words: List[String]) {
  val titleTextReport: Text = new Text {
    text = words(0)
    style = "-fx-font: normal bold 50pt sans-serif"
    fill = new LinearGradient(
      endX = 0,
      stops = Stops(Red, DarkRed))
  }

  val titleTextGenerator: Text = new Text {
    text = words(1)
    style = "-fx-font: italic bold 50pt sans-serif"
    fill = new LinearGradient(
      endX = 0,
      stops = Stops(White, DarkGray)
    )
    effect = new DropShadow {
      color = DarkGray
      radius = 15
      spread = 0.25
    }
  }

  def toTitle: String = {
    words.mkString("")
  }
}

object MainTitle {
  def apply(words: List[String]): MainTitle = new MainTitle(words)
}

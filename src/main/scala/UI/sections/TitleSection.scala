package fr.valle.report_generator
package UI.sections

import UI.DebugBorder
import UI.main.MainTitle

import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color

class TitleSection(mainTitle: MainTitle) extends IsASectionTrait {
  private val section: HBox = new HBox {
    border = DebugBorder(Color.Yellow).border
    children = Seq(mainTitle.titleTextReport, mainTitle.titleTextGenerator)
  }

  override def mySection: HBox = section
}

object TitleSection {
  def apply(mainTitle: MainTitle): TitleSection = new TitleSection(mainTitle)
}
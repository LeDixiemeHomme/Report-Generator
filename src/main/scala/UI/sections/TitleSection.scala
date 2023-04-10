package fr.valle.report_generator
package UI.sections

import UI.main.MainTitle

import scalafx.scene.layout.HBox

class TitleSection(mainTitle: MainTitle) extends IsASectionTrait {
  private val section: HBox = new HBox {
    children = Seq(mainTitle.titleTextReport, mainTitle.titleTextGenerator)
  }

  override def mySection: HBox = section
}

object TitleSection {
  def apply(mainTitle: MainTitle): TitleSection = new TitleSection(mainTitle)
}
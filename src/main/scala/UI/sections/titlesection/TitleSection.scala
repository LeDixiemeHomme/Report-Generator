package fr.valle.report_generator
package UI.sections.titlesection

import UI.DebugBorder
import UI.sections.IsASectionTrait
import UI.sections.titlesection.titles.IsATitleTrait

import scalafx.geometry.{Insets, Pos}
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color

class TitleSection(title: IsATitleTrait) extends IsASectionTrait {
  private val section: HBox = new HBox {
    border = DebugBorder(Color.Yellow).border
    alignment = Pos.Center
    margin = Insets(10, 0, 10, 0)
    children = title.myWords
  }

  override def mySection: HBox = section
}

object TitleSection {
  def apply(title: IsATitleTrait): TitleSection = new TitleSection(title)
}
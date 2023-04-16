package fr.valle.report_generator
package UI.sections.titlesection

import UI.DebugBorder
import UI.sections.IsASectionTrait

import scalafx.geometry.Pos
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color

class TitleSection(title: IsATitleTrait) extends IsASectionTrait {
  private val section: HBox = new HBox {
    border = DebugBorder(Color.Yellow).border
    alignment = Pos.Center
    children = title.myWords
  }

  override def mySection: HBox = section
}

object TitleSection {
  def apply(title: IsATitleTrait): TitleSection = new TitleSection(title)
}
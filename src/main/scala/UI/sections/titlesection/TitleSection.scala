package fr.valle.report_generator
package UI.sections.titlesection

import UI.sections.IsASectionTrait
import UI.sections.titlesection.titles.IsATitleTrait
import UI.{DebugBorder, Shaper}

import scalafx.geometry.{Insets, Pos}
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color

import java.nio.file.Paths

class TitleSection(title: IsATitleTrait) extends IsASectionTrait {
  private def image = new ImageView(new Image("file:" + Paths.get("src/main/resources/images/", "rg200.png").toAbsolutePath.toString))
  image.fitHeight = 150
  image.fitWidth = 150

  private val section: HBox = new HBox {
    border = DebugBorder(Color.Yellow).border
    alignment = Pos.Center
    margin = Insets(10, 0, 10, 0)
    children = title.myWords
  }

  override def mySection: HBox = {
    if (!Shaper.smallHeightScreenMode)
      return section
    new HBox()
  }
}

object TitleSection {
  def apply(title: IsATitleTrait): TitleSection = new TitleSection(title)
}
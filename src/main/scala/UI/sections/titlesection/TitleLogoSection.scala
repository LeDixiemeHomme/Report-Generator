package fr.valle.report_generator
package UI.sections.titlesection

import UI.sections.IsASectionTrait
import UI.sections.titlesection.titles.IsATitleTrait
import UI.{DebugBorder, Shaper}

import scalafx.geometry.Pos
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color

class TitleLogoSection(title: IsATitleTrait) extends IsASectionTrait {
  private def logo = new ImageView(new Image(getClass.getResource("/images/rgGeorgia100.png").toString))

  logo.fitHeight = 100
  logo.fitWidth = 100

  private val titleSection: HBox = new HBox {
    border = DebugBorder(Color.Yellow).border
    alignment = Pos.Center
    children = title.myWords
  }

  private val logoSection: HBox = new HBox {
    border = DebugBorder(Color.Blue).border
    alignment = Pos.Center
    children = logo
  }

  override def mySection: HBox = {
    if (!Shaper.smallHeightScreenMode)
      return new HBox {
        alignment = Pos.Center
        children = Seq(titleSection, logoSection)
      }
    new HBox()
  }
}

object TitleLogoSection {
  def apply(title: IsATitleTrait): TitleLogoSection = new TitleLogoSection(title)
}
package fr.valle.report_generator
package UI

import UI.sections.IsASectionTrait

import scalafx.geometry.{Insets, Pos}
import scalafx.scene.layout.{HBox, VBox}
import scalafx.scene.paint.Color

class MainContent(listSection: List[IsASectionTrait]) {
  private val vBox: VBox = new VBox {
    border = DebugBorder(Color.Green).border
    alignment = Pos.Center
    padding = Insets(10)
    prefWidth = 1600
    children = listSection.map(_.mySection)
  }

  //todo resize and replace areas
  val myMainContent: HBox = new HBox {
    border = DebugBorder(Color.Blue).border
    alignment = Pos.Center
    padding = Insets(5, 80, 5, 80)
    prefWidth = 1600
    children = vBox
  }
}

object MainContent {
  def apply(listSection: List[IsASectionTrait]): MainContent = new MainContent(listSection)
}

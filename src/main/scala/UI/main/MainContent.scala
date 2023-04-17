package fr.valle.report_generator
package UI.main

import UI.DebugBorder

import scalafx.geometry.{Insets, Pos}
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color

class MainContent(mainVBox: MainVBox) {
  //todo resize and replace areas
  val mainContent: HBox = new HBox {
    border = DebugBorder(Color.Blue).border
    alignment = Pos.Center
    padding = Insets(5, 80, 5, 80)
    prefWidth = 1600
    children = mainVBox.vBox
  }
}

object MainContent {
  def apply(mainVBox: MainVBox): MainContent = new MainContent(mainVBox)
}

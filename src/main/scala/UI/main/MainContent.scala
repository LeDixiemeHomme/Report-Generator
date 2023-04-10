package fr.valle.report_generator
package UI.main

import scalafx.geometry.{Insets, Pos}
import scalafx.scene.layout.HBox

class MainContent(mainVBox: MainVBox) {
  val mainContent: HBox = new HBox {
    alignment = Pos.Center
    padding = Insets(50, 80, 50, 80)
    children = mainVBox.vBox
  }
}

object MainContent {
  def apply(mainVBox: MainVBox): MainContent = new MainContent(mainVBox)
}

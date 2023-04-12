package fr.valle.report_generator
package UI.main

import UI.DebugBorder
import UI.sections.IsASectionTrait

import scalafx.geometry.{Insets, Pos}
import scalafx.scene.layout.VBox
import scalafx.scene.paint.Color

class MainVBox(sectionSeq: Seq[IsASectionTrait]) {
  val vBox: VBox = new VBox {
    border = DebugBorder(Color.Green).border
    alignment = Pos.Center
    padding = Insets(10)
    prefWidth = 700
    children = sectionSeq.map(_.mySection)
  }
}

object MainVBox {
  def apply(sectionSeq: Seq[IsASectionTrait]): MainVBox = new MainVBox(sectionSeq)
}

package fr.valle.report_generator
package UI.main

import UI.sections.IsASectionTrait

import scalafx.geometry.{Insets, Pos}
import scalafx.scene.layout.VBox

class MainVBox(sectionSeq: Seq[IsASectionTrait]) {
  val vBox: VBox = new VBox {
    alignment = Pos.Center
    padding = Insets(10)
    children = sectionSeq.map(_.mySection)
  }
}

object MainVBox {
  def apply(sectionSeq: Seq[IsASectionTrait]): MainVBox = new MainVBox(sectionSeq)
}

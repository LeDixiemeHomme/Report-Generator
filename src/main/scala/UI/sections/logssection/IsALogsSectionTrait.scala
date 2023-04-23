package fr.valle.report_generator
package UI.sections.logssection

import UI.sections.IsASectionTrait

import scalafx.scene.control.ScrollPane
import scalafx.scene.layout.VBox

trait IsALogsSectionTrait extends IsASectionTrait {
  def myVBox: VBox
  def myScrollPane: ScrollPane
}

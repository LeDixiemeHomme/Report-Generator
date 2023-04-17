package fr.valle.report_generator
package UI.sections

import UI.sections.logssection.IsALogsSectionTrait

import org.apache.logging.log4j.scala.Logging
import scalafx.scene.control.Button
import scalafx.scene.layout.{HBox, VBox}
import scalafx.scene.text.Text

class LogsSection extends Logging with IsALogsSectionTrait {
  private val vbox: VBox = new VBox{
  }

  private val section: HBox = new HBox {
    prefHeight = 300
    prefWidth = 1600
    style = "-fx-background-color: white;"
    children = vbox
  }
  override def mySection: HBox = new HBox {
    children = Seq(section)
  }

  override def myVBox: VBox = vbox
}

object LogsSection {
  def apply(): LogsSection = new LogsSection()
}

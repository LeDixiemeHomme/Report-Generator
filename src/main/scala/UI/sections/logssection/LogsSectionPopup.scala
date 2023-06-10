package fr.valle.report_generator
package UI.sections.logssection

import logging.{Levels, Log}

import org.apache.logging.log4j.scala.Logging
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.ScrollPane
import scalafx.scene.layout.{HBox, VBox}
import scalafx.scene.paint.Color
import scalafx.scene.text.Text

class LogsSectionPopup extends Logging with IsALogsSectionTrait {
  private val logsMessageVBox: VBox = new VBox {
    mouseTransparent = false
  }
  private val scrollPane: ScrollPane = new ScrollPane {
    padding = Insets(0, 10, 0, 10)
    prefHeight = 600
    prefWidth = 1200
    content = logsMessageVBox
    mouseTransparent = false
  }
  private val section: HBox = new HBox {
    children = scrollPane
    alignment = Pos.Center
  }

  private def logToColor(log: Log): Color = log.level match {
    case Levels.TRACE => Color.Black
    case Levels.DEBUG => Color.Blue
    case Levels.INFO => Color.Black
    case Levels.WARN => Color.Orange
    case Levels.ERROR => Color.Red
    case Levels.FATAL => Color.Red
  }

  private def addLogToMyVBox(log: Log): Unit = {
    myVBox.children.add(
      new Text {
        text = log.message
        fill = logToColor(log = log)
        style = "-fx-font-size: 15px"
        mouseTransparent = false
        pickOnBounds = true
      }
    )
  }

  override def mySection: HBox = new HBox {
    children = Seq(section)
    alignment = Pos.Center
  }

  override def myVBox: VBox = logsMessageVBox

  override def myScrollPane: ScrollPane = scrollPane

  override def update(log: Log): Unit = addLogToMyVBox(log = log)
}

object LogsSectionPopup {
  def apply(): LogsSectionPopup = new LogsSectionPopup()
}
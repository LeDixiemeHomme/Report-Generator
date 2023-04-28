package fr.valle.report_generator
package UI.sections.logssection

import org.apache.logging.log4j.scala.Logging
import scalafx.scene.control.ScrollPane
import scalafx.scene.layout.{HBox, VBox}
import scalafx.scene.paint.Color
import scalafx.scene.text.Text

class LogsSection extends Logging with IsALogsSectionTrait {
  private val vbox: VBox = new VBox {
    children.onChange { (_, _) =>
      scrollDown(scrollPane)
    }
    mouseTransparent = false
  }
  private val scrollPane: ScrollPane = new ScrollPane {
    prefHeight = 300
    prefWidth = 1600
    content = vbox
    vvalue.onChange {
      this.setVvalue(1.0)
    }
    mouseTransparent = false
  }
  private val section: HBox = new HBox {
    style = "-fx-background-color: white;"
    children = scrollPane
  }

  private def scrollDown(scrollPane: ScrollPane): Unit = {
    scrollPane.setVvalue(1.0)
  }
  private def addLogToMyVBox(log :String): Unit = {
    myVBox.children.add(
      new Text {
        text = log
        fill = Color.Red
        style = "-fx-font-size: 15px"
        mouseTransparent = false
        pickOnBounds = true
      }
    )
  }

  override def mySection: HBox = new HBox {
    children = Seq(section)
  }

  override def myVBox: VBox = vbox

  override def myScrollPane: ScrollPane = scrollPane

  override def update(log: String): Unit = addLogToMyVBox(log = log)
}

object LogsSection {
  def apply(): LogsSection = new LogsSection()
}

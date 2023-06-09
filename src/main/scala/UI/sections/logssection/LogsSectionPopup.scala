package fr.valle.report_generator
package UI.sections.logssection

import org.apache.logging.log4j.scala.Logging
import scalafx.geometry.Insets
import scalafx.scene.control.ScrollPane
import scalafx.scene.layout.{HBox, VBox}
import scalafx.scene.paint.Color
import scalafx.scene.text.Text

class LogsSectionPopup extends Logging with IsALogsSectionTrait {
  private val vbox: VBox = new VBox {
//    children.onChange { (_, _) =>
//      scrollDown(scrollPane)
//    }
    mouseTransparent = false
  }
  private val scrollPane: ScrollPane = new ScrollPane {
    padding = Insets(0, 10, 0, 10)
    prefHeight = 500
    prefWidth = 1100
    content = vbox
//    vvalue.onChange {
//      this.setVvalue(1.0)
//    }
    mouseTransparent = false
  }
  private val section: HBox = new HBox {
    children = scrollPane
  }

  private def scrollDown(scrollPane: ScrollPane): Unit = {
    scrollPane.setVvalue(1.0)
  }

  private def addLogToMyVBox(log: String): Unit = {
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

object LogsSectionPopup {
  def apply(): LogsSectionPopup = new LogsSectionPopup()
}
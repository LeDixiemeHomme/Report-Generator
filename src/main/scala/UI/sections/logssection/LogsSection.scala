package fr.valle.report_generator
package UI.sections.logssection

import org.apache.logging.log4j.scala.Logging
import scalafx.scene.control.ScrollPane
import scalafx.scene.layout.{HBox, VBox}

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

  override def mySection: HBox = new HBox {
    children = Seq(section)
  }

  override def myVBox: VBox = vbox

  override def myScrollPane: ScrollPane = scrollPane
}

object LogsSection {
  def apply(): LogsSection = new LogsSection()
}

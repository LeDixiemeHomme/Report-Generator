package fr.valle.report_generator
package UI.stages.logsstages

import UI.sections.logssection.{IsALogsSectionTrait, LogsSectionPopup}

import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Node
import scalafx.scene.layout.HBox
import scalafx.stage.Stage

class LogsStage extends IsALogsStageTrait {
  private val logsSection: IsALogsSectionTrait = LogsSectionPopup()

  private val myStage: Stage = {
    val closeButton = createCloseButton
    val copyButton = createCopyButton
    val childrenValue: Seq[Node] = Seq(
      logsSection.mySection,
      new HBox {
        padding = Insets(25, 0, 0, 0)
        alignment = Pos.Center
        children = Seq(createButtonHBox(button = copyButton), createButtonHBox(button = closeButton))
      }
    )
    createStage(closeButton = closeButton, titleValue = "Logs", childrenValue = childrenValue)
  }

  override def showMyStage(): Unit = {
    myStage.showAndWait()
  }
}

object LogsStage {
  def apply(): LogsStage = new LogsStage()
}

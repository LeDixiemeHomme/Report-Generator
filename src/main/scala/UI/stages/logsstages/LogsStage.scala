package fr.valle.report_generator
package UI.stages.logsstages

import UI.sections.logssection.{IsALogsSectionTrait, LogsSectionPopup}

import scalafx.scene.Node
import scalafx.stage.Stage

class LogsStage extends IsALogsStageTrait {
  private val logsSection: IsALogsSectionTrait = LogsSectionPopup()

  private val myStage: Stage = {
    val closeButton = createCloseButton
    val childrenValue: Seq[Node] = Seq(
      logsSection.mySection,
      createCloseButtonHBox(closeButton = closeButton),
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

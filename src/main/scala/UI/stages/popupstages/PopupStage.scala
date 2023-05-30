package fr.valle.report_generator
package UI.stages.popupstages

import scalafx.scene.Node
import scalafx.scene.paint.Color
import scalafx.scene.text.Text
import scalafx.stage.{Modality, Stage}

class PopupStage(popupMessage: String, fileLocation: String, isSuccess: Boolean) extends IsAPopupStageTrait {

  private val myStage: Stage = {
    val fileLocText = if (isSuccess) new Text(fileLocation) else new Text("vbenoitaàeraerdf")
    fileLocText.fill = Color.White
    val closeButton = createCloseButton
    val childrenValue: Seq[Node] = Seq(
      createResultTextHBox(isSuccess = isSuccess),
      createPopupMessageHBox(popupMessage),
      createFileLocationTextHBox(isSuccess = isSuccess, fileLocation = fileLocation),
      createCloseButtonHBox(closeButton = closeButton)
    )
    createStage(closeButton = closeButton, titleValue = "Success", childrenValue = childrenValue)
  }

  override def showMyStage(): Unit = {
    myStage.initModality(Modality.ApplicationModal)
    myStage.showAndWait()
  }
}

object PopupStage {
  def apply(popupMessage: String, fileLocation: String, isSuccess: Boolean): PopupStage = new PopupStage(popupMessage, fileLocation, isSuccess)
}

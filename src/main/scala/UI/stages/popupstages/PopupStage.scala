package fr.valle.report_generator
package UI.stages.popupstages

import UI.DebugBorder

import scalafx.scene.Node
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color
import scalafx.scene.text.Text
import scalafx.stage.{Modality, Stage}

class PopupStage(val popupMessage: String, val fileLocation: String, val isSuccess: Boolean) extends IsAPopupStageTrait {

  private def myStage: Stage = {

    val fileLocText = if (isSuccess) new Text(fileLocation) else new Text("vbenoitaàeraerdf")
    fileLocText.fill = Color.White
    val closeButton = createCloseButton
    val childrenValue: Seq[Node] = Seq(
      new HBox {
        border = DebugBorder(Color.Blue).border
        children = Seq(
          createResultTextHBox(isSuccess = isSuccess),
          createOpenFileImageHBox(isSuccess = isSuccess, fileLocation = fileLocation)
        )
      },
      createPopupMessageHBox(popupMessage),
      createCloseButtonHBox(closeButton = closeButton),
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

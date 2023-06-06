package fr.valle.report_generator
package UI.stages.popupstages

import UI.DebugBorder

import scalafx.geometry.Pos
import scalafx.scene.Node
import scalafx.scene.layout.VBox
import scalafx.scene.paint.Color
import scalafx.scene.text.Text
import scalafx.stage.{Modality, Stage}

class PopupStage(val popupMessage: String, val fileLocation: String, val isSuccess: Boolean) extends IsAPopupStageTrait {

  private def createResultHBox(fileLocation: String, isSuccess: Boolean): VBox = new VBox {
    border = DebugBorder(Color.Green).border
    alignment = Pos.Center
    children = Seq(
      createResultTextHBox(isSuccess = isSuccess),
      createOpenFileImageVBox(isSuccess = isSuccess, fileLocation = fileLocation)
    )
  }

  private val myStage: Stage = {
    val fileLocText = if (isSuccess) new Text(fileLocation) else new Text("vbenoitaàeraerdf")
    fileLocText.fill = Color.White
    val closeButton = createCloseButton
    val childrenValue: Seq[Node] = Seq(
      createResultHBox(fileLocation = fileLocation, isSuccess = isSuccess),
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

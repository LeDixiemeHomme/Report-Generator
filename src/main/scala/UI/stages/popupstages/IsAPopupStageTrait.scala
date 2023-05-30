package fr.valle.report_generator
package UI.stages.popupstages

import UI.DebugBorder
import UI.stages.IsAStageTrait

import scalafx.geometry.Pos
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color
import scalafx.scene.text.Text

trait IsAPopupStageTrait extends IsAStageTrait {
  def createPopupMessageHBox(popupMessage: String): HBox = new HBox {
    border = DebugBorder(Color.Red).border
    children = new Text {
      text = popupMessage
      fill = Color.White
      alignment = Pos.Center
      style = "-fx-font-size: 20px"
    }
  }

  def createResultTextHBox(isSuccess: Boolean): HBox = new HBox {
    border = DebugBorder(Color.Orange).border
    children = new Text {
      text = if (isSuccess) "Succès" else "Échec"
      fill = if (isSuccess) Color.Green else Color.Red
      alignment = Pos.Center
      style = "-fx-font-size: 30px"
    }
  }

  def createFileLocationTextHBox(isSuccess: Boolean, fileLocation: String): HBox = new HBox {
    border = DebugBorder(Color.Yellow).border
    children = new Text {
      text = if (isSuccess) fileLocation else "vbenoitaàeraerdf"
      fill = Color.White
      alignment = Pos.Center
      style = "-fx-font-size: 15px"
    }
  }
}

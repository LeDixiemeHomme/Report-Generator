package fr.valle.report_generator
package UI.stages.popupstages

import UI.DebugBorder
import UI.stages.IsAStageTrait

import scalafx.geometry.Pos
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color
import scalafx.scene.text.Text

import java.nio.file.Paths
import scala.sys.process.stringSeqToProcess

trait IsAPopupStageTrait extends IsAStageTrait {

  def createOpenFileImageHBox(isSuccess: Boolean, fileLocation: String): HBox = {
    val resourcePath = "src/main/resources/images/"
    val openFilePath = Paths.get(resourcePath, "open-file-100.png").toAbsolutePath.toString
    val openFileOverPath = Paths.get(resourcePath, "open-file-over-100.png").toAbsolutePath.toString
    val openFileFailureCrossPath = Paths.get(resourcePath, "open-file-failure-cross-100.png").toAbsolutePath.toString

    val image = new Image("file:" + openFilePath)
    val imageOver = new Image("file:" + openFileOverPath)
    val imageFailure = new Image("file:" + openFileFailureCrossPath)
    var imageView = new ImageView(imageFailure)

    if (isSuccess) {
      imageView = new ImageView(image)
      imageView.setOnMouseClicked(_ => {
        // faire quelque chose lorsqu'on clique sur l'image
        println("L'image a été cliquée")
        Seq("cmd", "/c", "start", fileLocation).!
      })

      imageView.setOnMouseEntered(_ => {
        println("setOnMouseEntered")
        imageView.setImage(imageOver)
      })

      imageView.setOnMouseExited(_ => {
        println("setOnMouseExited")
        imageView.setImage(image)
      })
    }
    new HBox {
      children = imageView
    }
  }
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

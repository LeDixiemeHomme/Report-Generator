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

  private def createImageView(imageFileName: String) = {
    val imageView = new ImageView(
      new Image("file:" + Paths.get("src/main/resources/images/", imageFileName).toAbsolutePath.toString)
    )
    imageView.fitHeight = 70
    imageView.fitWidth = 70
    imageView
  }

  def createOpenFileImageHBox(isSuccess: Boolean, fileLocation: String): HBox = {
    val resourcePath = "src/main/resources/images/"
    val openFilePath = Paths.get(resourcePath, "open-file-100.png").toAbsolutePath.toString
    val openFileOverPath = Paths.get(resourcePath, "open-file-over-100.png").toAbsolutePath.toString

    var imageView = createImageView(imageFileName = "open-file-failure-cross-100.png")

    if (isSuccess) {
      imageView = createImageView(imageFileName = "open-file-100.png")
      imageView.setOnMouseClicked(_ => {
        Seq("cmd", "/c", "start", fileLocation).!
      })

      imageView.setOnMouseExited(_ => imageView.setImage(new Image("file:" + openFilePath)))
      imageView.setOnMouseEntered(_ => imageView.setImage(new Image("file:" + openFileOverPath)))
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

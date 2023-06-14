package fr.valle.report_generator
package UI.stages.popupstages

import UI.DebugBorder
import UI.stages.IsAStageTrait
import features.services.{OpenDocxReport, StringToParagraphService}

import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Node
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.{HBox, VBox}
import scalafx.scene.paint.Color
import scalafx.scene.text.Text

import java.nio.file.Paths
import scala.collection.mutable.ListBuffer

trait IsAPopupStageTrait extends IsAStageTrait {

  private def createImageView(imageFileName: String) = {
    val imageView = new ImageView(
      new Image("file:" + Paths.get("src/main/resources/images/", imageFileName).toAbsolutePath.toString)
    )
    imageView.fitHeight = 70
    imageView.fitWidth = 70
    imageView
  }

  def createOpenFileImageVBox(isSuccess: Boolean, fileLocation: String): VBox = {
    val resourcePath = "src/main/resources/images/"
    val openFilePath = Paths.get(resourcePath, "open-file-100.png").toAbsolutePath.toString
    val openFileOverPath = Paths.get(resourcePath, "open-file-over-100.png").toAbsolutePath.toString

    var imageView = createImageView(imageFileName = "open-file-failure-cross-100.png")

    if (isSuccess) imageView = createImageView(imageFileName = "open-file-100.png")

    val buffer: ListBuffer[Node] = new ListBuffer[Node]
    buffer.append(imageView)

    val subImageText = new Text {
      text = "Ouvrir le fichier"
      fill = Color.LightBlue
      style = "-fx-font-size: 15px"
      underline = true
    }

    if (isSuccess) buffer.append(subImageText)

    val box = new VBox {
      border = DebugBorder(Color.Red).border
      margin = Insets(0, 100, 0, 100)
      alignment = Pos.Center
      children = buffer.toList
    }

    if (isSuccess) {
      box.setOnMouseClicked(_ => {
        OpenDocxReport().open(fileLocation = fileLocation)
      })
      box.setOnMouseExited(_ => {
        imageView.setImage(new Image("file:" + openFilePath))
        subImageText.fill = Color.LightBlue
      })
      box.setOnMouseEntered(_ => {
        imageView.setImage(new Image("file:" + openFileOverPath))
        subImageText.fill = Color.White
      })
    }

    box
  }

  private def createPopupText(textValue: String): Text = new Text {
    text = textValue
    fill = Color.White
    style = "-fx-font-size: 20px"
  }

  private def createPopupMessageTextSeq(popupMessage: String, limiter: Int): Seq[Text] = popupMessage match {
    case "" => Seq.empty
    case _ => stringListToTextSeq(sentencesList = StringToParagraphService().action(popupMessage = popupMessage, limiter = limiter))
  }

  private def stringListToTextSeq(sentencesList: List[String]): Seq[Text] = {
    sentencesList.map(sentence => createPopupText(sentence))
  }

  def createPopupMessageHBox(popupMessage: String): HBox = new HBox {
    alignment = Pos.Center
    padding = Insets(15, 0, 15, 0)
    children = new VBox {
      border = DebugBorder(Color.Yellow).border
      children = createPopupMessageTextSeq(popupMessage = popupMessage, limiter = 50)
    }
  }

  def createResultTextHBox(isSuccess: Boolean): HBox = new HBox {
    border = DebugBorder(Color.Purple).border
    padding = Insets(0, 50, 0, 50)
    children = new Text {
      border = DebugBorder(Color.Blue).border
      text = if (isSuccess) "Succès" else "Échec"
      fill = if (isSuccess) Color.Green else Color.Red
      alignment = Pos.Center
      style = "-fx-font-size: 40px"
    }
  }
}

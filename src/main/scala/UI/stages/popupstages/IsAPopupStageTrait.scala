package fr.valle.report_generator
package UI.stages.popupstages

import UI.DebugBorder
import UI.stages.IsAStageTrait
import features.services.StringToParagraphService

import scalafx.geometry.Pos
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.{HBox, VBox}
import scalafx.scene.paint.Color
import scalafx.scene.text.{Text, TextAlignment}

import java.nio.file.Paths
import scala.collection.mutable.ListBuffer
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
      border = DebugBorder(Color.Red).border
      children = imageView
    }
  }

  private def createPopupText(textValue: String): Text = new Text {
    text = textValue
    fill = Color.White
    style = "-fx-font-size: 20px"
  }

  protected def createPopupMessageTextSeq(popupMessage: String, limiter: Int): Seq[Text] = popupMessage match {
    case "" => Seq.empty
    case _ => stringListToTextSeq(sentencesList = StringToParagraphService().action(popupMessage = popupMessage, limiter = limiter))
  }

  private def stringListToTextSeq(sentencesList: List[String]): Seq[Text] = {
    sentencesList.map(sentence => createPopupText(sentence))
  }

  private def temp(popupMessage: String, limiter: Int): Seq[Text] = {

    val childBuffer: ListBuffer[Text] = ListBuffer()

    val popupMessageWords: List[String] = popupMessage.split(" ").toList

    val stringBuffer = new StringBuffer()

    stringBuffer.append(popupMessageWords.head)

    popupMessageWords.tail.foreach(word => {
      if (stringBuffer.length() < limiter && (stringBuffer.length() + word.length) < limiter) {
        stringBuffer.append(" " + word)
      } else {
        childBuffer.addOne(createPopupText(textValue = stringBuffer.toString))
        stringBuffer.delete(0, stringBuffer.length)
        stringBuffer.append(word)
      }
    })

    childBuffer.addOne(createPopupText(textValue = stringBuffer.toString))
    childBuffer.foreach(text => text.textAlignment = TextAlignment.Center)
    childBuffer.toSeq
  }

  def createPopupMessageHBox(popupMessage: String): HBox = new HBox {
    alignment = Pos.Center
    border = DebugBorder(Color.Red).border
    children = new VBox {
      children = createPopupMessageTextSeq(popupMessage = popupMessage, limiter = 50)
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

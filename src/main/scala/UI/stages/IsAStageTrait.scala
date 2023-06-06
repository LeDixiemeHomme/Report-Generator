package fr.valle.report_generator
package UI.stages

import UI.DebugBorder
import UI.styles.CloseButtonStyles.{unselectedEnteredButtonStyle, unselectedExitedButtonStyle}

import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.Button
import scalafx.scene.layout.{Background, BackgroundFill, HBox, VBox}
import scalafx.scene.paint.{Color, CycleMethod, LinearGradient}
import scalafx.scene.{Node, Scene}
import scalafx.stage.Stage

trait IsAStageTrait {
  def showMyStage(): Unit

  def createCloseButton: Button = new Button {
    prefWidth = 100
    prefHeight = 50
    style = unselectedExitedButtonStyle
    onMouseEntered = _ => style = unselectedEnteredButtonStyle
    onMouseExited = _ => style = unselectedExitedButtonStyle
    text = "Fermer"
  }

  def createCloseButtonHBox(closeButton: Button): HBox = new HBox {
    border = DebugBorder(Color.Orange).border
    padding = Insets(5, 0, 5, 0)
    children = closeButton
    alignment = Pos.Center
  }

  def createStage(closeButton: Button, titleValue: String, childrenValue: Seq[Node]): Stage = new Stage {
    title = titleValue
    scene = new Scene {
      fill = Color.rgb(38, 38, 38)
      root = new VBox {
        border = DebugBorder(Color.Blue).border
        alignment = Pos.Center
        padding = Insets(20, 100, 20, 100)
        children = childrenValue
        background = new Background(Array(
          new BackgroundFill(new LinearGradient(0, 0, 1, 0, true, CycleMethod.NoCycle),
            null, null)))
      }
    }
    closeButton.onAction = _ => close()
  }
}

package fr.valle.report_generator
package UI.stages

import UI.DebugBorder
import UI.styles.CloseButtonStyles.{unselectedEnteredButtonStyle, unselectedExitedButtonStyle}

import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.Button
import scalafx.scene.layout.{HBox, VBox}
import scalafx.scene.paint.Color
import scalafx.scene.{Node, Scene}
import scalafx.stage.Stage

trait IsAStageTrait {
  def showMyStage(): Unit

  def createCloseButton: Button = new Button {
    style = unselectedExitedButtonStyle
    onMouseEntered = _ => style = unselectedEnteredButtonStyle
    onMouseExited = _ => style = unselectedExitedButtonStyle
    text = "Fermer"
  }

  def createCloseButtonHBox(closeButton: Button): HBox = new HBox {
    border = DebugBorder(Color.Green).border
    children = closeButton
    alignment = Pos.Center
  }

  def createStage(closeButton: Button, titleValue: String, childrenValue: Seq[Node]): Stage = new Stage {
    title = titleValue
    scene = new Scene {
      fill = Color.rgb(38, 38, 38)
      content = new VBox {
        border = DebugBorder(Color.Pink).border
        alignment = Pos.Center
        padding = Insets(20, 100, 20, 100)
        children = childrenValue
      }
    }
    closeButton.onAction = _ => close()
  }
}

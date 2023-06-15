package fr.valle.report_generator
package UI.stages.logsstages

import UI.stages.IsAStageTrait
import UI.styles.StageButtonStyles.{unselectedEnteredButtonStyle, unselectedExitedButtonStyle}
import logging.LogsKeeper

import scalafx.scene.control.Button
import scalafx.scene.input.{Clipboard, ClipboardContent}

trait IsALogsStageTrait extends IsAStageTrait {
  def createCopyButton: Button = new Button {
    prefWidth = 150
    prefHeight = 50
    style = unselectedExitedButtonStyle
    onMouseEntered = _ => style = unselectedEnteredButtonStyle
    onMouseExited = _ => style = unselectedExitedButtonStyle
    text = "Copier Logs"
    onAction = _ => {
      val content = new ClipboardContent()
      content.putString(LogsKeeper.myFormattedLogs)
      Clipboard.systemClipboard.setContent(content)
    }
  }
}

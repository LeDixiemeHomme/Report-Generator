package fr.valle.report_generator
package UI.sections.pagesection.pagecontent.form.formsections.browsebuttonstrategypattern

import UI.styles.BrowseButtonStyles.{unselectedEnteredButtonStyle, unselectedExitedButtonStyle}

import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.control.{Button, TextField}

trait BrowseButtonStrategyTrait {
  def browseButton: Button = new Button {
    style = unselectedExitedButtonStyle

    onMouseEntered = _ => {
      style = unselectedEnteredButtonStyle
    }
    onMouseExited = _ => {
      style = unselectedExitedButtonStyle
    }
  }
  def optionalBrowseButton(myTextField: TextField, stage: PrimaryStage): Option[Button]
}

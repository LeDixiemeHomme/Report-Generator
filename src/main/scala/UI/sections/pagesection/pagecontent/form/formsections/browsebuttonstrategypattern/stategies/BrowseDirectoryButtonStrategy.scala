package fr.valle.report_generator
package UI.sections.pagesection.pagecontent.form.formsections.browsebuttonstrategypattern.stategies

import UI.sections.pagesection.pagecontent.form.formsections.browsebuttonstrategypattern.BrowseButtonStrategyTrait

import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.control.{Button, TextField}
import scalafx.scene.paint.Color
import scalafx.stage.DirectoryChooser

object BrowseDirectoryButtonStrategy extends BrowseButtonStrategyTrait {
  override def browseButton(myTextField: TextField, stage: PrimaryStage): Button = new Button {
    text = "Choisir un dossier"
    textFill = Color.Red
    onAction = _ => {
      val directoryChooser = new DirectoryChooser()
      val selectedDirectory = directoryChooser.showDialog(stage)
      if (selectedDirectory != null) {
        myTextField.text = selectedDirectory.getPath
      }
    }
  }
}

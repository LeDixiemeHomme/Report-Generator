package fr.valle.report_generator
package UI.sections.pagesection.pagecontent.form.formsections.browsebuttonstrategypattern.stategies

import UI.sections.pagesection.pagecontent.form.formsections.browsebuttonstrategypattern.BrowseButtonStrategyTrait

import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.control.{Button, TextField}
import scalafx.stage.DirectoryChooser

object BrowseDirectoryButtonStrategy extends BrowseButtonStrategyTrait {
  override def optionalBrowseButton(myTextField: TextField, stage: PrimaryStage): Option[Button] = {
    val button: Button = browseButton
    button.text = "Choisir un dossier"
    button.onAction = _ => {
      val directoryChooser = new DirectoryChooser()
      val selectedDirectory = directoryChooser.showDialog(stage)
      if (selectedDirectory != null) {
        myTextField.text = selectedDirectory.getPath
      }
    }
    Some(button)
  }
}

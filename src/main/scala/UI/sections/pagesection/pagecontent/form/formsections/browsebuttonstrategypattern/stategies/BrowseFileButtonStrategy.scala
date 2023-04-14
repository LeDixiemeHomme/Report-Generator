package fr.valle.report_generator
package UI.sections.pagesection.pagecontent.form.formsections.browsebuttonstrategypattern.stategies

import UI.sections.pagesection.pagecontent.form.formsections.browsebuttonstrategypattern.BrowseButtonStrategyTrait

import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.control.{Button, TextField}
import scalafx.stage.FileChooser

object BrowseFileButtonStrategy extends BrowseButtonStrategyTrait {
  override def browseButton(myTextField: TextField, stage: PrimaryStage): Button = new Button {
    text = "Choisir un fichier"
    onAction = _ => {
      val fileChooser = new FileChooser()
      val selectedFile = fileChooser.showOpenDialog(stage)
      if (selectedFile != null) {
        myTextField.text = selectedFile.getPath
      }
    }
  }
}

package fr.valle.report_generator
package UI.sections.pagesection.pagecontent.form.formsections.browsebuttonstrategypattern

import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.control.{Button, TextField}

trait BrowseButtonStrategyTrait {
  def browseButton(myTextField: TextField, stage: PrimaryStage): Button
}

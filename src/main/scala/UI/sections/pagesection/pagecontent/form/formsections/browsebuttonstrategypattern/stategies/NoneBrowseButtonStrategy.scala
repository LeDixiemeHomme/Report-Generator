package fr.valle.report_generator
package UI.sections.pagesection.pagecontent.form.formsections.browsebuttonstrategypattern.stategies

import UI.sections.pagesection.pagecontent.form.formsections.browsebuttonstrategypattern.BrowseButtonStrategyTrait

import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.control.{Button, TextField}

object NoneBrowseButtonStrategy extends BrowseButtonStrategyTrait {
  override def optionalBrowseButton(myTextField: TextField, stage: PrimaryStage): Option[Button] = None
}

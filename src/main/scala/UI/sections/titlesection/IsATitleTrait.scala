package fr.valle.report_generator
package UI.sections.titlesection

import scalafx.scene.text.Text

trait IsATitleTrait {
  def myWords: List[Text]
  def toTitle: String
}

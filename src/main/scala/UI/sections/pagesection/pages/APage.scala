package fr.valle.report_generator
package UI.sections.pagesection.pages

import scalafx.scene.layout.VBox

class APage(body: VBox) {
  val myBody: VBox = body
}

object APage {
  def apply(body: VBox): APage = new APage(body)
}
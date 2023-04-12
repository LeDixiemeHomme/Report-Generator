package fr.valle.report_generator
package UI.sections.pagesection.pages

import scalafx.scene.layout.VBox

class Page(body: VBox) {
  val myBody: VBox = body
}

object Page {
  def apply(body: VBox): Page = new Page(body)
}
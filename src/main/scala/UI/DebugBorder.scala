package fr.valle.report_generator
package UI

import scalafx.scene.layout.{Border, BorderStroke, BorderStrokeStyle, BorderWidths}
import scalafx.scene.paint.Color

class DebugBorder(borderColor: Color) {
  val border: Border = new Border(new BorderStroke(borderColor, BorderStrokeStyle.Solid, null, new BorderWidths(2)))
}

object DebugBorder {
  def apply(borderColor: Color): DebugBorder = new DebugBorder(borderColor)
}
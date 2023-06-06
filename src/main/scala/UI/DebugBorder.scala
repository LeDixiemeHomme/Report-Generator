package fr.valle.report_generator
package UI

import UI.DebugBorder.DEBUG_MODE

import scalafx.scene.layout.{Border, BorderStroke, BorderStrokeStyle, BorderWidths}
import scalafx.scene.paint.Color

class DebugBorder(borderColor: Color) {
  var border: Border = _
  if (DEBUG_MODE) {
    border = new Border(new BorderStroke(borderColor, BorderStrokeStyle.Solid, null, new BorderWidths(2)))
  } else {
    border = new Border(new BorderStroke(borderColor, null, null, new BorderWidths(2)))
  }
}

object DebugBorder {
  def apply(borderColor: Color): DebugBorder = new DebugBorder(borderColor)

  final val DEBUG_MODE: Boolean = false
//  final val DEBUG_MODE: Boolean = true
}
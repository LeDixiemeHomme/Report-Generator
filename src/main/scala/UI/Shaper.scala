package fr.valle.report_generator
package UI

import logging.LogsKeeper

import org.apache.logging.log4j.scala.Logging
import scalafx.stage.Screen

object Shaper extends Logging {
  var smallHeightScreenMode: Boolean = _
  var smallWidthScreenMode: Boolean = _

  private val bounds = Screen.primary.visualBounds

  LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.INFO, s"Screen size: ${bounds.width} x ${bounds.height}", classFrom = getClass)

  if (bounds.height < 1000.0) {
    smallHeightScreenMode = true
  } else {
    smallHeightScreenMode = false
  }

  if (bounds.width < 1600.0) {
    smallWidthScreenMode = true
  } else {
    smallWidthScreenMode = false
  }

  LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.INFO, s"Small width screen mode: ${smallWidthScreenMode}", classFrom = getClass)
  LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.INFO, s"Small height screen mode: ${smallHeightScreenMode}", classFrom = getClass)
}

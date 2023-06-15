package fr.valle.report_generator
package UI

import logging.{Levels, Log, LogsKeeper}

import org.apache.logging.log4j.scala.Logging
import scalafx.stage.Screen

object Shaper extends Logging {
  var smallHeightScreenMode: Boolean = _
  var smallWidthScreenMode: Boolean = _

  private val bounds = Screen.primary.visualBounds

  LogsKeeper.keepAndLog(extLogger = logger, log = Log(message = s"Screen size: ${bounds.width} x ${bounds.height}", level = Levels.INFO), classFrom = getClass)


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

  smallHeightScreenMode = false
  smallWidthScreenMode = true

  LogsKeeper.keepAndLog(extLogger = logger, log = Log(message = s"Small width screen mode: ${smallWidthScreenMode}", level = Levels.INFO), classFrom = getClass)
  LogsKeeper.keepAndLog(extLogger = logger, log = Log(message = s"Small height screen mode: ${smallHeightScreenMode}", level = Levels.INFO), classFrom = getClass)
}

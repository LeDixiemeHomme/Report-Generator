package fr.valle.report_generator

import UI.MainStage
import logging.LogsKeeper

import org.apache.logging.log4j.scala.Logging
import scalafx.application.{JFXApp3, Platform}

object MainReportGeneratorJFXApp extends Logging with JFXApp3 {
  Platform.startup(runnable = () => {})

  override def start(): Unit = {
    stage = MainStage().myStage

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.INFO, "Starting app", classFrom = getClass)
  }
}

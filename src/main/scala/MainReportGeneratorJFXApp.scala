package fr.valle.report_generator

import UI.stages.{IsAStageTrait, MainStage}
import logging.LogsKeeper

import org.apache.logging.log4j.scala.Logging
import scalafx.application.{JFXApp3, Platform}

object MainReportGeneratorJFXApp extends Logging with JFXApp3 {
  Platform.startup(runnable = () => {})

  private def initStage(stage: IsAStageTrait): JFXApp3.PrimaryStage = {
    stage.myStage
  }

  override def start(): Unit = {
    stage = initStage(MainStage())

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.INFO, "Starting app", classFrom = getClass)
  }
}

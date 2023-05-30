package fr.valle.report_generator

import logging.LogsKeeper
import fr.valle.report_generator.UI.stages.primarystages.{IsAPrimaryStageTrait, MainPrimaryStage}

import org.apache.logging.log4j.scala.Logging
import scalafx.application.{JFXApp3, Platform}

object MainReportGeneratorJFXApp extends Logging with JFXApp3 {
  Platform.startup(runnable = () => {})

  private def initStage(stage: IsAPrimaryStageTrait): JFXApp3.PrimaryStage = {
    stage.myPrimaryStage
  }

  override def start(): Unit = {
    stage = initStage(MainPrimaryStage())

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.INFO, "Starting app", classFrom = getClass)
  }
}

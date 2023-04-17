package fr.valle.report_generator
package logging

import UI.sections.LogsSection
import UI.sections.logssection.IsALogsSectionTrait
import customexceptions.UnknownLevelLogsKeeperException

import org.apache.logging.log4j.scala.{Logger, Logging}
import scalafx.scene.text.Text

import scala.collection.mutable.ListBuffer

object LogsKeeper extends Logging {
  private val logs: ListBuffer[Text] = new ListBuffer[Text]()
  private var myLogsSection: IsALogsSectionTrait = LogsSection()

  def setMyLogsSection(aLogsSection: IsALogsSectionTrait): Unit = {
    this.myLogsSection = aLogsSection
  }

  private def displayLog(extLogger: Logger, level: String, message: String): Unit = level match {
    case TRACE => extLogger.trace(message)
    case DEBUG => extLogger.debug(message)
    case INFO => extLogger.info(message)
    case WARN => extLogger.warn(message)
    case ERROR => extLogger.error(message)
    case FATAL => extLogger.fatal(message)
    case _ => throw new UnknownLevelLogsKeeperException(level = level)
  }

  final val TRACE: String = "TRACE"
  final val DEBUG: String = "DEBUG"
  final val INFO: String = "INFO"
  final val WARN: String = "WARN"
  final val ERROR: String = "ERROR"
  final val FATAL: String = "FATAL"

  def keepAndLog(extLogger: Logger, level: String, message: String, classFrom: Class[_]): Unit = {
    try {
      displayLog(extLogger = extLogger, message = message, level = level)
    } catch {
      case e: UnknownLevelLogsKeeperException =>
        keepAndLog(extLogger = logger, level = ERROR, message = e.getMessage, classFrom = getClass)
        keepAndLog(extLogger = extLogger, level = ERROR, message = message, classFrom = classFrom)
    }
    logs += new Text(classFrom.getName + ": " + message)
    this.myLogsSection.myVBox.children.add(new Text {
      text = classFrom.getName + ": " + message
      style = "-fx-font-size: 20px; -fx-background-color: white; -fx-text-fill: black; -fx-border-color: white; -fx-border-width: 2px;"
    })
  }

  def myLogs: List[Text] = logs.toList
}

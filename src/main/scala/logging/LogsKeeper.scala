package fr.valle.report_generator
package logging

import UI.sections.logssection.{IsALogsSectionTrait, LogsSection}
import customexceptions.UnknownLevelLogsKeeperException

import org.apache.logging.log4j.scala.{Logger, Logging}
import scalafx.scene.paint.Color
import scalafx.scene.text.Text

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import scala.collection.mutable.ListBuffer

object LogsKeeper extends Logging {
  private val logs: ListBuffer[String] = new ListBuffer[String]()
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

      val currentDateTime = LocalDateTime.now()
      val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

      val log: String = logs.length.toString + " -- " + currentDateTime.format(formatter) + " " + classFrom.getName + ": " + message
      logs += log
      this.myLogsSection.myVBox.children.add(
        new Text {
          text = log
          fill = Color.Red
          style = "-fx-font-size: 15px"
          mouseTransparent = false
          pickOnBounds = true
        }
      )
    } catch {
      case e: UnknownLevelLogsKeeperException =>
        keepAndLog(extLogger = logger, level = ERROR, message = e.getMessage, classFrom = getClass)
        keepAndLog(extLogger = extLogger, level = ERROR, message = message, classFrom = classFrom)
    }
  }

  def myLogs: List[String] = logs.toList
}

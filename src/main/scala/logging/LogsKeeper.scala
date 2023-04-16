package fr.valle.report_generator
package logging

import customexceptions.UnknownLevelLogsKeeperException

import org.apache.logging.log4j.scala.{Logger, Logging}
import scalafx.collections.ObservableBuffer
import scalafx.scene.text.Text

import scala.collection.mutable.ListBuffer

object LogsKeeper extends Logging {
  private val logs: ListBuffer[String] = new ListBuffer[String]()
  val buffer: ObservableBuffer[Text] = ObservableBuffer[Text]()
  buffer.add(new Text("test"))

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
    logs += classFrom.getName + ": " + message
    buffer.add(new Text("ta mere"))
  }

  def myLogs: List[String] = logs.toList
}

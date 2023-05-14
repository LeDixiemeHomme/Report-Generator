package fr.valle.report_generator
package logging

import customexceptions.{EmptyXWPFDocumentException, UnknownLevelLogsKeeperException}
import logging.loggingobserverpattern.LoggingSubject

import org.apache.logging.log4j.scala.{Logger, Logging}

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import scala.collection.mutable.ListBuffer

object LogsKeeper extends Logging with LoggingSubject {

  private val logs: ListBuffer[String] = new ListBuffer[String]()

  private def printLogToConsole(extLogger: Logger, level: String, message: String): Unit = level match {
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
      printLogToConsole(extLogger = extLogger, message = message, level = level)
      notifyLogSection(message, classFrom)
    } catch {
      case unknownLevelLogsKeeperException: UnknownLevelLogsKeeperException =>
        handleError(extLogger = extLogger, exception = unknownLevelLogsKeeperException, classFrom = classFrom)
    }
  }

  def handleError(extLogger: Logger, exception: Exception, classFrom: Class[_]): Unit = exception match {
    case exc: EmptyXWPFDocumentException =>
      printLogToConsole(extLogger = extLogger, message = exc.toString, level = ERROR)
      notifyLogSection("Le modèle de document est vide, il faut ajouter du contenu et des balises pour que le programme génère un rapport", classFrom)
    case exc: UnknownLevelLogsKeeperException =>
      printLogToConsole(extLogger = extLogger, message = exc.toString, level = ERROR)
      notifyLogSection(exc.getMessage, classFrom)
  }

  def myLogs: List[String] = logs.toList

  private def notifyLogSection(message: String, classFrom: Class[_]): Unit = {
    val log: String = buildLogMessage(message, classFrom)
    logs += log
    notifyLoggingObservers(log = log)
  }

  private def buildLogMessage(message: String, classFrom: Class[_]): String = {
    val currentDateTime = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    logs.length.toString + " -- " + currentDateTime.format(formatter) + " " + classFrom.getName + ": " + message
  }
}

package fr.valle.report_generator
package logging

import customexceptions.{EmptyXWPFDocumentException, UnknownLevelLogsKeeperException}
import logging.Levels.Level
import logging.loggingobserverpattern.LoggingSubjectTrait

import org.apache.logging.log4j.scala.{Logger, Logging}

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import scala.collection.mutable.ListBuffer

object LogsKeeper extends Logging with LoggingSubjectTrait {

  private val logs: ListBuffer[Log] = new ListBuffer[Log]()

  /**
   * @throws UnknownLevelLogsKeeperException if the `level` is not actually an existing level
   */
  @throws(classOf[UnknownLevelLogsKeeperException])
  private def printLogToConsole(extLogger: Logger, level: Level, message: String): Unit = level match {
    case Levels.TRACE => extLogger.trace(message)
    case Levels.DEBUG => extLogger.debug(message)
    case Levels.INFO => extLogger.info(message)
    case Levels.WARN => extLogger.warn(message)
    case Levels.ERROR => extLogger.error(message)
    case Levels.FATAL => extLogger.fatal(message)
    case _ => throw UnknownLevelLogsKeeperException(level = level)
  }

  def keepAndLog(extLogger: Logger, log: Log, classFrom: Class[_]): Unit = {
    try {
      printLogToConsole(extLogger = extLogger, message = log.message, level = log.level)
      notifyLogSection(log, classFrom)
    } catch {
      case unknownLevelLogsKeeperException: UnknownLevelLogsKeeperException =>
        handleError(extLogger = extLogger, exception = unknownLevelLogsKeeperException, classFrom = classFrom)
    }
  }

  def handleError(extLogger: Logger, exception: Exception, classFrom: Class[_]): Unit = exception match {
    case exc: EmptyXWPFDocumentException =>
      printLogToConsole(extLogger = extLogger, message = exc.toString, level = Levels.ERROR)
      notifyLogSection(log = Log(message = "Le modèle de document est vide, il faut ajouter du contenu et des balises pour que le programme génère un rapport", level = Levels.ERROR), classFrom)
    case exc: UnknownLevelLogsKeeperException =>
      printLogToConsole(extLogger = extLogger, message = exc.toString, level = Levels.ERROR)
      notifyLogSection(log = Log(message = exc.getMessage, level = Levels.ERROR), classFrom)
    case defaultExc: Exception =>
      printLogToConsole(extLogger = extLogger, message = "Default exception handling !!!!!! : " + defaultExc.toString, level = Levels.ERROR)
      notifyLogSection(log = Log(message = defaultExc.getMessage, level = Levels.ERROR), classFrom)
  }

  private def myLogs: List[Log] = logs.toList

  def myFormattedLogs: String = myLogs.map(log => log.message).mkString("\n")

  private def notifyLogSection(log: Log, classFrom: Class[_]): Unit = {
    val formattedLog: Log = buildLogMessage(log = log, classFrom = classFrom)
    logs += formattedLog
    notifyLoggingObservers(log = formattedLog)
  }

  private def buildLogMessage(log: Log, classFrom: Class[_]): Log = {
    val currentDateTime = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

//    val message: String = logs.length.toString + " -- " + currentDateTime.format(formatter) + " " + classFrom.getName + ": " + log.message

    val message: String = s"${logs.length.toString} -- ${log.level} -- ${currentDateTime.format(formatter)} ${classFrom.getName}: ${log.message}"

    Log(message = message, level = log.level)
  }
}

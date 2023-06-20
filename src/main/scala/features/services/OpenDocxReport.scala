package fr.valle.report_generator
package features.services

import app.os.LocalOS
import app.path.FilePath
import logging.{Levels, Log, LogsKeeper}

import org.apache.logging.log4j.scala.Logging

import scala.sys.process._

class OpenDocxReport extends Logging {
  def open(fileLocation: String): Int = {
    val filePath: FilePath = FilePath.stringToFilePath(stringValue = fileLocation)

    LocalOS.os match {
      case LocalOS.OSs.WINDOWS => Seq("powershell", "/c", s"start '${filePath.constructBasePathAntiSlash}${filePath.fileName.value}.${filePath.extension}'").!
      case _ => LogsKeeper.keepAndLog(extLogger = logger, log = Log(message = s"Not implemented yet for your operating system", level = Levels.ERROR), classFrom = getClass); 0
    }
  }
}

object OpenDocxReport {
  def apply(): OpenDocxReport = new OpenDocxReport()
}
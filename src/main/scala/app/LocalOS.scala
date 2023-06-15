package fr.valle.report_generator
package app

import app.LocalOS.OSs.OS
import logging.{Levels, Log, LogsKeeper}

import org.apache.logging.log4j.scala.Logging

object LocalOS extends Logging {
  val os: OS = OSs.fromSystemProperties(System.getProperty("os.name"))
  LogsKeeper.keepAndLog(extLogger = logger, log = Log(message = s"Operating system: $os", level = Levels.INFO), classFrom = getClass)

  object OSs extends Enumeration {
    type OS = Value
    val WINDOWS, LINUX, MACOS, OTHER = Value

    def fromString(s: String): Option[OS] = {
      OSs.values.find(_.toString == s)
    }

    def fromSystemProperties(property: String): OS = {
      property.toLowerCase match {
        case prop if prop.contains("windows") => WINDOWS
        case prop if prop.contains("linux") => LINUX
        case prop if prop.contains("mac") => MACOS
        case _ => OTHER
      }
    }
  }
}

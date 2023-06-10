package fr.valle.report_generator
package logging

import logging.Levels.Level

case class Log(message: String, level: Level)

object Levels extends Enumeration {
  type Level = Value
  val TRACE, DEBUG, INFO, WARN, ERROR, FATAL = Value

  def fromString(s: String): Option[Level] = {
    Levels.values.find(_.toString == s)
  }
}
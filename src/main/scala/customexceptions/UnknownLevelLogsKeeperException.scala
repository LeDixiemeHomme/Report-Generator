package fr.valle.report_generator
package customexceptions

import logging.Levels.Level

case class UnknownLevelLogsKeeperException(level: Level, cause: Option[Throwable] = None)
  extends Exception(String.format("Niveau de log inconnu: %s", level.toString)) {
  initCause(cause.orNull)
}
package fr.valle.report_generator
package customexceptions

class UnknownLevelLogsKeeperException(val level: String, val cause: Option[Throwable] = None)
  extends Exception(String.format("Niveau de log inconnu : %s", level)) {
  initCause(cause.orNull)
}
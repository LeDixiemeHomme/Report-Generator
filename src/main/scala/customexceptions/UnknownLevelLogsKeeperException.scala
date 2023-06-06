package fr.valle.report_generator
package customexceptions

case class UnknownLevelLogsKeeperException(level: String, cause: Option[Throwable] = None)
  extends Exception(String.format("Niveau de log inconnu: %s", level)) {
  initCause(cause.orNull)
}
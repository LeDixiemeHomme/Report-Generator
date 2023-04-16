package fr.valle.report_generator
package customexceptions

class UnknownLevelLogsKeeperException(level: String) extends Exception(
  String.format("Niveau de log inconnu : %s", level)
)
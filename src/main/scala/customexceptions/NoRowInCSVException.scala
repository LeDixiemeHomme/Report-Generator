package fr.valle.report_generator
package customexceptions

case class NoRowInCSVException(cause: Option[Throwable] = None)
  extends Exception("Le fichier de données CSV ne contient pas de données.") {
  initCause(cause.orNull)
}

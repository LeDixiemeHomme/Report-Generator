package fr.valle.report_generator
package customexceptions

class NoRowInCSVException(val cause: Option[Throwable] = None)
  extends Exception("Le fichier de données CSV ne contient pas de données.") {
  initCause(cause.orNull)
}

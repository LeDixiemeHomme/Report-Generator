package fr.valle.report_generator
package customexceptions

class MissingCSVColumnException(noSuchElementExceptionMessage: String, val cause: Option[Throwable] = None)
  extends Exception(String.format("La colonne %s est manquante dans le fichier de données CSV.", noSuchElementExceptionMessage.split(" ").last)) {
  initCause(cause.orNull)
}
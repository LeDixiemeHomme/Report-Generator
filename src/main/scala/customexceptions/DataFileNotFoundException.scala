package fr.valle.report_generator
package customexceptions

class DataFileNotFoundException(filePath: String, val cause: Option[Throwable] = None)
  extends Exception(String.format("Le fichier de données %s est introuvable.", filePath)) {
  initCause(cause.orNull)
}

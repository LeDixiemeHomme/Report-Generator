package fr.valle.report_generator
package customexceptions

case class WrongFileFormatException(fileType: String, cause: Option[Throwable] = None)
  extends Exception(String.format("Mauvais format de fichier: %s", fileType)) {
  initCause(cause.orNull)
}

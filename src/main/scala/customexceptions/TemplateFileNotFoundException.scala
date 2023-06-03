package fr.valle.report_generator
package customexceptions

case class TemplateFileNotFoundException(filePath: String, cause: Option[Throwable] = None)
  extends Exception(String.format("Le fichier template \"%s\" est introuvable.", filePath)) {
  initCause(cause.orNull)
}
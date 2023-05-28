package fr.valle.report_generator
package customexceptions

class TemplateFileNotFoundException(filePath: String, val cause: Option[Throwable] = None)
  extends Exception(String.format("Le fichier template \"%s\" est introuvable.", filePath)) {
  initCause(cause.orNull)
}
package fr.valle.report_generator
package customexceptions

case class EmptyXWPFDocumentException(templateFilePath: String, cause: Option[Throwable] = None)
  extends Exception(String.format("Le document word template %s est vide.", templateFilePath)) {
  initCause(cause.orNull)
}

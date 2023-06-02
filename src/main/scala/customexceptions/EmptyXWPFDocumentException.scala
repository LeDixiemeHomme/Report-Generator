package fr.valle.report_generator
package customexceptions

class EmptyXWPFDocumentException(val templateFilePath: String, val cause: Option[Throwable] = None)
  extends Exception(String.format("Le document word template %s est vide.", templateFilePath)) {
  initCause(cause.orNull)
}

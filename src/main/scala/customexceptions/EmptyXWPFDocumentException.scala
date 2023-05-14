package fr.valle.report_generator
package customexceptions

class EmptyXWPFDocumentException(val cause: Option[Throwable] = None)
  extends Exception(String.format("Le XWPFDocument est vide.")) {
  initCause(cause.orNull)
}

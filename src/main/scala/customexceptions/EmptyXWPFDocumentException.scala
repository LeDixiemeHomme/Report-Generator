package fr.valle.report_generator
package customexceptions

class EmptyXWPFDocumentException(val cause: Option[Throwable] = None)
  extends Exception(String.format("Le document word template est vide.")) {
  initCause(cause.orNull)
}

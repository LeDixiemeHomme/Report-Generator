package fr.valle.report_generator
package customexceptions

class OutputDirNotFoundException(outputDirPath: String, val cause: Option[Throwable] = None)
  extends Exception(String.format("Le dossier de destination \"%s\" est introuvable.", outputDirPath)) {
  initCause(cause.orNull)
}
package fr.valle.report_generator
package customexceptions

case class OutputDirNotFoundException(outputDirPath: String, cause: Option[Throwable] = None)
  extends Exception(String.format("Le dossier de destination \"%s\" est introuvable.", outputDirPath)) {
  initCause(cause.orNull)
}
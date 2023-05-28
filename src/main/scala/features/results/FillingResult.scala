package fr.valle.report_generator
package features.results

case class FillingResult(isSuccess: Boolean, completionMessage: String, filledDocRelativePath: String, outputFilePath: String) extends IsAResultTrait {
  override def toString: String =
    s"ReceptionReportData{Completion Message: $completionMessage, Filled Doc Relative Path: $filledDocRelativePath, Output File Path: $outputFilePath}"
}
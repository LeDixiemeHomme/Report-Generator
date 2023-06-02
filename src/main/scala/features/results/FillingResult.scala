package fr.valle.report_generator
package features.results

case class FillingResult(isSuccess: Boolean, popUpMessage: String, filledDocRelativePath: String, outputFilePath: String) extends IsAResultTrait {
  override def toString: String =
    s"ReceptionReportData{Completion Message: $popUpMessage, Filled Doc Relative Path: $filledDocRelativePath, Output File Path: $outputFilePath}"
}
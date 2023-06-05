package fr.valle.report_generator
package features.results

import domain.path.FilePath

case class FillingResult(isSuccess: Boolean, popUpMessage: String, filledDocRelativePath: FilePath) extends IsAResultTrait {
  override def toString: String =
    s"ReceptionReportData{Completion Message: $popUpMessage, Filled Doc Relative Path: ${filledDocRelativePath.constructFinalPath}, Output File Path: ${filledDocRelativePath.basePath}}"
}
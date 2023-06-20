package fr.valle.report_generator
package features.results

import app.path.FilePath

case class FillingResult(isSuccess: Boolean, popUpMessage: String, filledDocRelativePath: Option[FilePath]) extends IsAResultTrait {
  override def toString: String = {
    val filledDocRelativePathToString: String = if (filledDocRelativePath.nonEmpty) filledDocRelativePath.get.toString else "None"
    s"FillingResult{isSuccess: $isSuccess, popUpMessage: $popUpMessage, filledDocRelativePath: $filledDocRelativePathToString}"
  }
}
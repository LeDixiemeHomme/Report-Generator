package fr.valle.report_generator
package features.results

import app.path.FilePath

case class GenerateReceptionReportFeatureResult(isSuccess: Boolean, popUpMessage: String, fileLocationPath: Option[FilePath]) extends IsAResultTrait {
  override def toString: String = {
    val fileLocationPathToString: String = if (fileLocationPath.nonEmpty) fileLocationPath.get.toString else "None"
    s"GenerateReceptionReportFeatureResult{isSuccess: $isSuccess, popUpMessage: $popUpMessage, fileLocationPath: $fileLocationPathToString}"
  }
}
package fr.valle.report_generator
package features.results

import domain.path.FilePath

case class GenerateReceptionReportFeatureResult(isSuccess: Boolean, popUpMessage: String, fileLocationPath: Option[FilePath]) extends IsAResultTrait
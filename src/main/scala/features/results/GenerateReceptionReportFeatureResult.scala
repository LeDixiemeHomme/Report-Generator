package fr.valle.report_generator
package features.results

case class GenerateReceptionReportFeatureResult(isSuccess: Boolean, popUpMessage: String, fileLocation: Option[String]) extends IsAResultTrait
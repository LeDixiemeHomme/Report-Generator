package fr.valle.report_generator
package features.results

case class ProcessingResult(isSuccess: Boolean, popUpMessage: String, processedData: Map[String, String]) extends IsAResultTrait {
  override def toString: String = s"ProcessingResult{isSuccess: $isSuccess, popUpMessage: $popUpMessage, processedData: $processedData}"
}

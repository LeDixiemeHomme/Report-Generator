package fr.valle.report_generator
package features.results

case class ProcessingResult(isSuccess: Boolean, processedData: Map[String, String]) extends IsAResultTrait {
  override def toString: String = s"ProcessingResult : $processedData"
}

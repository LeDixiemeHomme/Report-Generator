package fr.valle.report_generator
package services.processing

class ProcessingResult(val processedData: Map[String, String]) {
  override def toString: String = s"ProcessingResult : $processedData"
}

object ProcessingResult {
  def apply(processedData: Map[String, String]): ProcessingResult = new ProcessingResult(processedData)
}

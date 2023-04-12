package fr.valle.report_generator
package services.processing

trait ProcessingServiceTrait {
  def process(dataToProcess: List[Any]): ProcessingResult
}

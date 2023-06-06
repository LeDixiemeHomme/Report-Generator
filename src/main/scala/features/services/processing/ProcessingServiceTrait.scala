package fr.valle.report_generator
package features.services.processing

import domain.processor.InputDataToMapValueProcessor.ToMapValueProcessorTrait
import features.results.ProcessingResult

trait ProcessingServiceTrait[A] {
  def process(dataToProcess: A)(implicit parser: ToMapValueProcessorTrait[A]): ProcessingResult
}

package fr.valle.report_generator
package services.processing

import domain.processor.InputDataToMapValueProcessor.ToMapValueProcessorTrait

trait ProcessingServiceTrait[A] {
  def process(dataToProcess: A)(implicit parser: ToMapValueProcessorTrait[A]): ProcessingResult
}

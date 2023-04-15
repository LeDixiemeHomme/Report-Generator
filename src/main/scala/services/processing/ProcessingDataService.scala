package fr.valle.report_generator
package services.processing

import domain.processor.InputDataToMapValueProcessor
import domain.processor.InputDataToMapValueProcessor.ToMapValueProcessorTrait

class ProcessingDataService[A]() extends ProcessingServiceTrait[A] {
  override def process(dataToProcess: A)(implicit toMapProcessor: ToMapValueProcessorTrait[A]): ProcessingResult = {
    val mapValues: Map[String, String] = InputDataToMapValueProcessor.processToMapValue(inputData = dataToProcess)
    ProcessingResult(mapValues)
  }
}

object ProcessingDataService {
  def apply[A](): ProcessingDataService[A] = new ProcessingDataService()
}
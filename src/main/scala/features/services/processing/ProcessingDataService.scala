package fr.valle.report_generator
package features.services.processing

import customexceptions.IncompleteObjectInstantiationException
import domain.processor.InputDataToMapValueProcessor
import domain.processor.InputDataToMapValueProcessor.ToMapValueProcessorTrait
import features.results.ProcessingResult
import logging.LogsKeeper

import org.apache.logging.log4j.scala.Logging

class ProcessingDataService[A]() extends Logging with ProcessingServiceTrait[A] {
  private val inputDataToMapValueProcessor: InputDataToMapValueProcessor = InputDataToMapValueProcessor()

  override def process(dataToProcess: A)(implicit toMapProcessor: ToMapValueProcessorTrait[A]): ProcessingResult = {

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.INFO, "Processing data", classFrom = getClass)

    var mapValues: Map[String, String] = Map()

    try {
      mapValues = inputDataToMapValueProcessor.processToMapValue(inputData = dataToProcess)
    } catch {
      case incompleteObjectInstantiationException: IncompleteObjectInstantiationException =>
        LogsKeeper.handleError(extLogger = logger, exception = incompleteObjectInstantiationException, classFrom = getClass)
        return ProcessingResult(isSuccess = false, mapValues)
    }

    ProcessingResult(isSuccess = true, mapValues)
  }
}

object ProcessingDataService {
  def apply[A](): ProcessingDataService[A] = new ProcessingDataService()
}
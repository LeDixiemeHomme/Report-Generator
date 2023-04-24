package fr.valle.report_generator
package services.processing

import domain.processor.InputDataToMapValueProcessor
import domain.processor.InputDataToMapValueProcessor.ToMapValueProcessorTrait
import logging.LogsKeeper

import org.apache.logging.log4j.scala.Logging

class ProcessingDataService[A]() extends Logging with ProcessingServiceTrait[A] {
  override def process(dataToProcess: A)(implicit toMapProcessor: ToMapValueProcessorTrait[A]): ProcessingResult = {

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.INFO, "Processing data", classFrom = getClass)

    val mapValues: Map[String, String] = InputDataToMapValueProcessor.processToMapValue(inputData = dataToProcess)
    ProcessingResult(mapValues)
  }
}

object ProcessingDataService {
  def apply[A](): ProcessingDataService[A] = new ProcessingDataService()
}
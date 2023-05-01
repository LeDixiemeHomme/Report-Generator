package fr.valle.report_generator
package domain.processor

import domain.processor.InputDataToMapValueProcessor.ToMapValueProcessorTrait
import logging.LogsKeeper

import org.apache.logging.log4j.scala.Logging

class InputDataToMapValueProcessor extends Logging {

  def processToMapValue[A](inputData: A)(implicit inputDataType: ToMapValueProcessorTrait[A]): Map[String, String] = {

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.INFO, "InputDataToMapValueProcessor.processToMapValue()", classFrom = getClass)

    inputDataType.toMapValue(inputData)
  }
}

object InputDataToMapValueProcessor {
  def apply(): InputDataToMapValueProcessor = new InputDataToMapValueProcessor()

  trait ToMapValueProcessorTrait[A] {
    def toMapValue(inputData: A): Map[String, String]
  }
}

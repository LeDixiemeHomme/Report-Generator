package fr.valle.report_generator
package domain.processor

import customexceptions.IncompleteObjectInstantiationException
import domain.processor.InputDataToMapValueProcessor.ToMapValueProcessorTrait
import logging.LogsKeeper

import org.apache.logging.log4j.scala.Logging

import scala.util.{Failure, Success, Try}

class InputDataToMapValueProcessor extends Logging {

  def processToMapValue[A](inputData: A)(implicit processor: ToMapValueProcessorTrait[A]): Map[String, String] = {

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.INFO, "InputDataToMapValueProcessor.processToMapValue()", classFrom = getClass)

    val mapValue = tryProcessingDataSafely(inputData = inputData, processor = processor) match {
      case Success(mapValue) => mapValue

      case Failure(incompleteObjectInstantiationException: IncompleteObjectInstantiationException) => throw incompleteObjectInstantiationException
    }

    mapValue
  }

  private def tryProcessingDataSafely[A](inputData: A, processor: ToMapValueProcessorTrait[A]): Try[Map[String, String]] = {
    Try {
      processor.toMapValue(inputData)
    }
  }
}

object InputDataToMapValueProcessor {
  def apply(): InputDataToMapValueProcessor = new InputDataToMapValueProcessor()

  trait ToMapValueProcessorTrait[A] {
    /**
     * @throws IncompleteObjectInstantiationException if the `inputData` has a null value
     */
    @throws(classOf[IncompleteObjectInstantiationException])
    def toMapValue(inputData: A): Map[String, String]
  }
}

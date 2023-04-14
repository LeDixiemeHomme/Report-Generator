package fr.valle.report_generator
package domain.processor

object InputDataToMapValueProcessor {

  def processToMapValue[A](inputData: A)(implicit inputDataType: ToMapValueProcessorTrait[A]): Map[String, String] = {
    inputDataType.toMapValue(inputData)
  }

  trait ToMapValueProcessorTrait[A] {
    def toMapValue(inputData: A): Map[String, String]
  }
}

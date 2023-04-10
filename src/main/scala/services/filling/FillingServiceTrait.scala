package fr.valle.report_generator
package services.filling

trait FillingServiceTrait {
  def fill(templateFilePath: String, valuesMap: Map[String, String], outputFilePath: String): FillingResult
}

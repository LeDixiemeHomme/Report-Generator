package fr.valle.report_generator
package features.services.filling

import features.results.FillingResult


trait FillingServiceTrait {
  def fill(templateFilePath: String, valuesMap: Map[String, String], outputFilePath: String, fileName: Option[String]): FillingResult
}

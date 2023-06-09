package fr.valle.report_generator
package features.services.filling

import app.path.FilePath
import features.results.FillingResult


trait FillingServiceTrait {
  def fill(templateFilePath: FilePath, valuesMap: Map[String, String], outputFilePath: FilePath): FillingResult
}

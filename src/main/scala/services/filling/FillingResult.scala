package fr.valle.report_generator
package services.filling

class FillingResult(val completionMessage: String, val filledDocRelativePath: String, val outputFilePath: String) {
  override def toString: String = s"FillingResult : $completionMessage"
}

object FillingResult {
  def apply(completionMessage: String, filledDocRelativePath: String, outputFilePath: String): FillingResult = new FillingResult(completionMessage, filledDocRelativePath, outputFilePath)
}
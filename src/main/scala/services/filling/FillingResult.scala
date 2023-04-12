package fr.valle.report_generator
package services.filling

class FillingResult(val completionMessage: String) {
  override def toString: String = s"FillingResult : $completionMessage"
}

object FillingResult {
  def apply(result: String): FillingResult = new FillingResult(result)
}
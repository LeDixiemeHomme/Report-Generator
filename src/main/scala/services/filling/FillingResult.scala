package fr.valle.report_generator
package services.filling

class FillingResult(result: String) {
  override def toString: String = s"FillingResult : $result"
}

object FillingResult {
  def apply(result: String): FillingResult = new FillingResult(result)
}
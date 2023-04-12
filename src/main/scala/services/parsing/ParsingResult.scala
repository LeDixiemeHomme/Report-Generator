package fr.valle.report_generator
package services.parsing

class ParsingResult(val parsedData: List[Any]) {
  override def toString: String = s"ParsingResult : $parsedData"
}

object ParsingResult {
  def apply(parsedData: List[Any]): ParsingResult = new ParsingResult(parsedData)
}

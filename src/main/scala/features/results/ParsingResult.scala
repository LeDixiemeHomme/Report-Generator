package fr.valle.report_generator
package features.results

case class ParsingResult[A](isSuccess: Boolean, popUpMessage: String, parsedData: List[A]) extends IsAResultTrait {
  override def toString: String = s"ParsingResult : $parsedData"
}

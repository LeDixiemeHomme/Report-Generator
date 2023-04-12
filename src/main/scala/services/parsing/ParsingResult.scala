package fr.valle.report_generator
package services.parsing

class ParsingResult[A](val parsedData: List[A]) {
  override def toString: String = s"ParsingResult : $parsedData"
}

object ParsingResult {
  def apply[A](parsedData: List[A]): ParsingResult[A] = new ParsingResult(parsedData)
}

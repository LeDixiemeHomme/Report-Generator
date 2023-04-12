package fr.valle.report_generator
package services.parsing

class ParsingCsvService() extends ParsingServiceTrait {
  override def parse(): ParsingResult = ParsingResult(parsedData = 1 :: 2 :: Nil)
}

object ParsingCsvService {
  def apply(): ParsingCsvService = new ParsingCsvService()
}
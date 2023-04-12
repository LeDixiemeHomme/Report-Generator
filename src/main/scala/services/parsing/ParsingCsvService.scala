package fr.valle.report_generator
package services.parsing

import domain.parser.CsvParser
import domain.parser.CsvParser.FileParser

class ParsingCsvService[A]() extends ParsingServiceTrait[A] {
  override def parse(filePath: String)(implicit parser: FileParser[A]): ParsingResult[A] = {
    val dataList: List[A] = CsvParser.parseFile(filePath)
    ParsingResult(dataList)
  }
}

object ParsingCsvService {
  def apply[A](): ParsingCsvService[A] = new ParsingCsvService()
}
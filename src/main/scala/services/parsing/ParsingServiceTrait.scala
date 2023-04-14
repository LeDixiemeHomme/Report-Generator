package fr.valle.report_generator
package services.parsing

import domain.parser.CsvParser.FileParserTrait

trait ParsingServiceTrait[A] {
  def parse(filePath: String)(implicit parser: FileParserTrait[A]): ParsingResult[A]
}

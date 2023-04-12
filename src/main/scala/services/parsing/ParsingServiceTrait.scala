package fr.valle.report_generator
package services.parsing

import domain.parser.CsvParser.FileParser

trait ParsingServiceTrait[A] {
  def parse(filePath: String)(implicit parser: FileParser[A]): ParsingResult[A]
}

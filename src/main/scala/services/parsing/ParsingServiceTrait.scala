package fr.valle.report_generator
package services.parsing

import domain.parser.IsAnObjectParserTrait

trait ParsingServiceTrait[A] {
  def parse(filePath: String)(implicit parser: IsAnObjectParserTrait[A]): ParsingResult[A]
}

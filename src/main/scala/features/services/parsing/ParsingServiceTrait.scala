package fr.valle.report_generator
package features.services.parsing

import domain.parser.IsAnObjectParserTrait
import features.results.ParsingResult

trait ParsingServiceTrait[A] {
  def parse(filePath: String)(implicit parser: IsAnObjectParserTrait[A]): ParsingResult[A]
}

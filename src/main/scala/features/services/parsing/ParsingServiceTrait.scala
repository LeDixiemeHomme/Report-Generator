package fr.valle.report_generator
package features.services.parsing

import domain.parser.IsAnObjectParserTrait
import domain.path.FilePath
import features.results.ParsingResult

trait ParsingServiceTrait[A] {
  def parse(filePath: FilePath)(implicit parser: IsAnObjectParserTrait[A]): ParsingResult[A]
}

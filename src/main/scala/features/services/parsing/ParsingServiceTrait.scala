package fr.valle.report_generator
package features.services.parsing

import app.path.FilePath
import domain.csv.parser.IsAnObjectParserTrait
import features.results.ParsingResult

trait ParsingServiceTrait[A] {
  def parse(filePath: FilePath)(implicit parser: IsAnObjectParserTrait[A]): ParsingResult[A]
}

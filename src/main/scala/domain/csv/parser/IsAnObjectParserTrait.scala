package fr.valle.report_generator
package domain.csv.parser

import customexceptions.{MissingCSVColumnException, NoRowInCSVException}

trait IsAnObjectParserTrait[A] {
  /**
   * @throws MissingCSVColumnException if the `reader` has a missing column
   * @throws NoRowInCSVException       if there is no data row in the `reader`
   */
  @throws(classOf[MissingCSVColumnException])
  @throws(classOf[NoRowInCSVException])
  def parse(reader: Object): List[A]
}

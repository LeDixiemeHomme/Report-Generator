package fr.valle.report_generator
package domain.parser

import customexceptions.MissingCSVColumnException

trait IsAnObjectParserTrait[A] {
  /**
   * @throws MissingCSVColumnException if the `reader` has a missing column
   */
  @throws(classOf[MissingCSVColumnException])
  def parse(reader: Object): List[A]
}

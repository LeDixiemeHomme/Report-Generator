package fr.valle.report_generator
package domain.parser

import customexceptions.DataFileNotFoundException

trait IsACSVFileParserTrait {

  /**
   * @throws DataFileNotFoundException if the `csvFilePath` is not actually an existing file
   */
  @throws(classOf[DataFileNotFoundException])
  def parseFile[A](csvFilePath: String)(implicit objectParser: IsAnObjectParserTrait[A]): List[A]
}

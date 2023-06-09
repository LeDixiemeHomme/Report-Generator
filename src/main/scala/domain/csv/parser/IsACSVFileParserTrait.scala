package fr.valle.report_generator
package domain.csv.parser

import app.path.FilePath
import customexceptions.{DataFileNotFoundException, MissingCSVColumnException, NoRowInCSVException}

trait IsACSVFileParserTrait {

  /**
   * @throws DataFileNotFoundException if the `csvFilePath` is not actually an existing file
   * @throws MissingCSVColumnException if the `csvFilePath` is not actually an existing file
   * @throws NoRowInCSVException       if the `csvFilePath` is not actually an existing file
   */
  @throws(classOf[DataFileNotFoundException])
  @throws(classOf[MissingCSVColumnException])
  @throws(classOf[NoRowInCSVException])
  def parseFile[A](csvFilePath: FilePath)(implicit objectParser: IsAnObjectParserTrait[A]): List[A]
}

package fr.valle.report_generator
package domain.parser

trait IsACSVFileParserTrait {
  def parseFile[A](csvFilePath: String)(implicit objectParser: IsAnObjectParserTrait[A]): List[A]
}

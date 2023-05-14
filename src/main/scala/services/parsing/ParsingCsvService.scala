package fr.valle.report_generator
package services.parsing

import domain.parser.{IsACSVFileParserTrait, IsAnObjectParserTrait}
import logging.LogsKeeper

import org.apache.logging.log4j.scala.Logging

class ParsingCsvService[A](csvParserTrait: IsACSVFileParserTrait) extends Logging with ParsingServiceTrait[A] {
  private val csvParser: IsACSVFileParserTrait = csvParserTrait
  override def parse(filePath: String)(implicit parser: IsAnObjectParserTrait[A]): ParsingResult[A] = {

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.INFO, "Parsing csv file", classFrom = getClass)

    val dataList: List[A] = csvParser.parseFile(filePath)
    ParsingResult(dataList)
  }
}

object ParsingCsvService {
  def apply[A](CSVParserTrait: IsACSVFileParserTrait): ParsingCsvService[A] = new ParsingCsvService(CSVParserTrait)
}
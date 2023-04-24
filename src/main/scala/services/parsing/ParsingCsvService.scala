package fr.valle.report_generator
package services.parsing

import domain.parser.CsvParser
import domain.parser.CsvParser.FileParserTrait
import logging.LogsKeeper

import org.apache.logging.log4j.scala.Logging

class ParsingCsvService[A]() extends Logging with ParsingServiceTrait[A] {
  override def parse(filePath: String)(implicit parser: FileParserTrait[A]): ParsingResult[A] = {

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.INFO, "Parsing csv file", classFrom = getClass)

    val dataList: List[A] = CsvParser.parseFile(filePath)
    ParsingResult(dataList)
  }
}

object ParsingCsvService {
  def apply[A](): ParsingCsvService[A] = new ParsingCsvService()
}
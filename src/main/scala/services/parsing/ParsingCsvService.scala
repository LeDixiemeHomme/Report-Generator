package fr.valle.report_generator
package services.parsing

import customexceptions.DataFileNotFoundException
import domain.parser.{IsACSVFileParserTrait, IsAnObjectParserTrait}
import logging.LogsKeeper

import org.apache.logging.log4j.scala.Logging

import scala.collection.mutable.ListBuffer

class ParsingCsvService[A](csvParserTrait: IsACSVFileParserTrait) extends Logging with ParsingServiceTrait[A] {
  private val csvParser: IsACSVFileParserTrait = csvParserTrait

  override def parse(filePath: String)(implicit parser: IsAnObjectParserTrait[A]): ParsingResult[A] = {

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.INFO, "Parsing csv file", classFrom = getClass)

    val dataList: ListBuffer[A] = new ListBuffer[A]

    try {
      dataList.addAll(csvParser.parseFile(filePath))
    } catch {
      case dataFileNotFoundException: DataFileNotFoundException =>
        LogsKeeper.handleError(extLogger = logger, exception = dataFileNotFoundException, classFrom = getClass)
        return ParsingResult(Nil)
    }

    ParsingResult(dataList.toList)
  }
}

object ParsingCsvService {
  def apply[A](CSVParserTrait: IsACSVFileParserTrait): ParsingCsvService[A] = new ParsingCsvService(CSVParserTrait)
}
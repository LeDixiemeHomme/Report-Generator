package fr.valle.report_generator
package features.services.parsing

import customexceptions.{DataFileNotFoundException, MissingCSVColumnException, NoRowInCSVException}
import domain.parser.{IsACSVFileParserTrait, IsAnObjectParserTrait}
import domain.path.FilePath
import features.results.ParsingResult
import logging.{Levels, Log, LogsKeeper}

import org.apache.logging.log4j.scala.Logging

import scala.collection.mutable.ListBuffer

class ParsingCsvService[A](csvParserTrait: IsACSVFileParserTrait) extends Logging with ParsingServiceTrait[A] {
  private val csvParser: IsACSVFileParserTrait = csvParserTrait

  override def parse(filePath: FilePath)(implicit parser: IsAnObjectParserTrait[A]): ParsingResult[A] = {

    LogsKeeper.keepAndLog(extLogger = logger, log = Log(message = "Parsing csv file", level = Levels.DEBUG), classFrom = getClass)

    val dataList: ListBuffer[A] = new ListBuffer[A]

    try {
      dataList.addAll(csvParser.parseFile(filePath))
    } catch {
      case dataFileNotFoundException: DataFileNotFoundException =>
        LogsKeeper.handleError(extLogger = logger, exception = dataFileNotFoundException, classFrom = getClass)
        return ParsingResult(isSuccess = false, popUpMessage = dataFileNotFoundException.getMessage, parsedData = Nil)
      case missingCSVColumnException: MissingCSVColumnException =>
        LogsKeeper.handleError(extLogger = logger, exception = missingCSVColumnException, classFrom = getClass)
        return ParsingResult(isSuccess = false, popUpMessage = missingCSVColumnException.getMessage, parsedData = Nil)
      case noRowInCSVException: NoRowInCSVException =>
        LogsKeeper.handleError(extLogger = logger, exception = noRowInCSVException, classFrom = getClass)
        return ParsingResult(isSuccess = false, popUpMessage = noRowInCSVException.getMessage, parsedData = Nil)
    }

    ParsingResult(isSuccess = true, popUpMessage = "Successfully parsed", parsedData = dataList.toList)
  }
}

object ParsingCsvService {
  def apply[A](CSVParserTrait: IsACSVFileParserTrait): ParsingCsvService[A] = new ParsingCsvService(CSVParserTrait)
}
package fr.valle.report_generator
package domain.parser.tototoshiCSVparser

import customexceptions.{DataFileNotFoundException, MissingCSVColumnException, NoRowInCSVException}
import domain.parser.{IsACSVFileParserTrait, IsAnObjectParserTrait}
import logging.LogsKeeper

import com.github.tototoshi.csv.{CSVReader, DefaultCSVFormat}
import org.apache.logging.log4j.scala.Logging

import java.io.{File, FileNotFoundException}
import scala.util.{Failure, Success, Try}

class TototoshiCsvFileParser extends IsACSVFileParserTrait with Logging {

  override def parseFile[A](csvFilePath: String)(implicit objectParser: IsAnObjectParserTrait[A]): List[A] = {

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.INFO, "Parsing csv " + csvFilePath, classFrom = getClass)

    val listOfParsedObjects: List[A] = tryParseFileSafely(filePath = csvFilePath)(objectParser) match {
      case Success(parsedListOfA) => parsedListOfA
      case Failure(fileNotFoundException: FileNotFoundException) => throw new DataFileNotFoundException(filePath = csvFilePath, cause = Some(fileNotFoundException))
      case Failure(missingCSVColumnException: MissingCSVColumnException) => throw missingCSVColumnException
      case Failure(noRowInCSVException: NoRowInCSVException) => throw noRowInCSVException
      case Failure(exception) => throw exception
    }

    listOfParsedObjects
  }

  private def tryParseFileSafely[A](filePath: String)(implicit parser: IsAnObjectParserTrait[A]): Try[List[A]] = {
    Try {
      implicit object MyFormat extends DefaultCSVFormat {
        override val delimiter = ';'
      }

      val reader: CSVReader = CSVReader.open(new File(filePath))

      try {
        parser.parse(reader)
      } finally {
        reader.close()
      }
    }
  }
}

object TototoshiCsvFileParser {
  def apply(): TototoshiCsvFileParser = new TototoshiCsvFileParser()
}
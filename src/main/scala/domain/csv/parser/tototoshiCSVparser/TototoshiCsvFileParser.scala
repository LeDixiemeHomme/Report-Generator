package fr.valle.report_generator
package domain.csv.parser.tototoshiCSVparser

import app.path.FilePath
import customexceptions.{DataFileNotFoundException, MissingCSVColumnException, NoRowInCSVException}
import domain.csv.parser.{IsACSVFileParserTrait, IsAnObjectParserTrait}
import logging.{Levels, Log, LogsKeeper}

import com.github.tototoshi.csv.{CSVReader, DefaultCSVFormat}
import org.apache.logging.log4j.scala.Logging

import java.io.{File, FileNotFoundException}
import scala.util.{Failure, Success, Try}

class TototoshiCsvFileParser extends IsACSVFileParserTrait with Logging {

  override def parseFile[A](csvFilePath: FilePath)(implicit objectParser: IsAnObjectParserTrait[A]): List[A] = {

    LogsKeeper.keepAndLog(extLogger = logger, log = Log(message = s"Parsing csv $csvFilePath", level = Levels.INFO), classFrom = getClass)

    val listOfParsedObjects: List[A] = tryParseFileSafely(filePath = csvFilePath.constructFinalPath)(objectParser) match {
      case Success(parsedListOfA) => parsedListOfA
      case Failure(fileNotFoundException: FileNotFoundException) => throw DataFileNotFoundException(filePath = csvFilePath.constructFinalPath, cause = Some(fileNotFoundException))
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
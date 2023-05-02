package fr.valle.report_generator
package domain.parser

import domain.parser.CsvParser.FileParserTrait
import logging.LogsKeeper

import com.github.tototoshi.csv.{CSVReader, DefaultCSVFormat}
import org.apache.logging.log4j.scala.Logging

import java.io.File

class CsvParser extends Logging {
  def parseFile[A](filePath: String)(implicit parser: FileParserTrait[A]): List[A] = {

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.INFO, "Parsing csv " + filePath, classFrom = getClass)

    implicit object MyFormat extends DefaultCSVFormat {
      override val delimiter = ';'
    }

    val inputFile = new File(filePath)
    val reader = CSVReader.open(inputFile)

    val linesWithoutHeader = reader.all().drop(1)
    val parsedList = parser.parse(linesWithoutHeader)

    reader.close()
    parsedList
  }
}

object CsvParser {
  def apply(): CsvParser = new CsvParser()

  trait FileParserTrait[A] {
    def parse(lines: List[List[String]]): List[A]
  }
}
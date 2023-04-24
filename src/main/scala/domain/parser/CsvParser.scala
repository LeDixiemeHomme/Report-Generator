package fr.valle.report_generator
package domain.parser

import logging.LogsKeeper

import com.github.tototoshi.csv.CSVReader
import org.apache.logging.log4j.scala.Logging

import java.io.File

object CsvParser extends Logging {
  def parseFile[A](filePath: String)(implicit parser: FileParserTrait[A]): List[A] = {

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.INFO, "Parsing csv " + filePath, classFrom = getClass)

    val inputFile = new File(filePath)
    val reader = CSVReader.open(inputFile)

    val linesWithoutHeader = reader.all().drop(1)
    val parsedList = parser.parse(linesWithoutHeader)

    reader.close()
    parsedList
  }

  trait FileParserTrait[A] {
    def parse(lines: List[List[String]]): List[A]
  }

}
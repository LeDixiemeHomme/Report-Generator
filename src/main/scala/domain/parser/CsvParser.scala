package fr.valle.report_generator
package domain.parser

import com.github.tototoshi.csv.CSVReader

import java.io.File

object CsvParser {
  def parseFile[A](filePath: String)(implicit parser: FileParser[A]): List[A] = {
    val resourcePath = getClass.getResource(filePath).getPath
    val inputFile = new File(resourcePath)
    val reader = CSVReader.open(inputFile)

    val linesWithoutHeader = reader.all().drop(1)
    val parsedList = parser.parse(linesWithoutHeader)

    reader.close()
    parsedList
  }

  trait FileParser[A] {
    def parse(lines: List[List[String]]): List[A]
  }

}
package fr.valle.report_generator
package domain.model

import domain.parser.CsvParser.FileParser

case class InterventionData(name: String, age: Int)

object InterventionData {
  object InterventionDataParser extends FileParser[InterventionData] {
    def parse(lines: List[List[String]]): List[InterventionData] = {
      lines.map(row => {
        val name = row(0)
        val age = row(2).toInt
        InterventionData(name, age)
      })
    }
  }
}
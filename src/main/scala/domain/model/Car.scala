package fr.valle.report_generator
package domain.model

import domain.parser.CsvParser.FileParserTrait

case class Car(make: String, model: String, year: Int, color: String)

object Car {
  object CarParser extends FileParserTrait[Car] {
    def parse(lines: List[List[String]]): List[Car] = {
      lines.map(row => {
        val make = row(0)
        val model = row(1)
        val year = row(2).toInt
        val color = row(3)
        Car(make, model, year, color)
      })
    }
  }
}
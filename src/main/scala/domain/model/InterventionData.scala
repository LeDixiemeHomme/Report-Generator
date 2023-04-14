package fr.valle.report_generator
package domain.model

import domain.parser.CsvParser.FileParserTrait
import domain.processor.InputDataToMapValueProcessor.ToMapValueProcessorTrait

case class InterventionData(experimentName: String, date: String, sampleName: String, mass: Double, volume: Double, temperature: Double) {
  override def toString: String = {
    s"InterventionData: { $experimentName; Date: $date; Sample Name: $sampleName; Mass (g): $mass; Volume (mL): $volume; Temperature (°C): $temperature }"
  }
}

object InterventionData {
  object InterventionDataParser extends FileParserTrait[InterventionData] {
    def parse(lines: List[List[String]]): List[InterventionData] = {
      lines.map(row => {
        val experimentName = row(0)
        val date = row(1)
        val sampleName = row(2)
        val mass = row(3).toDouble
        val volume = row(4).toDouble
        val temperature = row(5).toDouble
        InterventionData(experimentName, date, sampleName, mass, volume, temperature)
      })
    }
  }
}
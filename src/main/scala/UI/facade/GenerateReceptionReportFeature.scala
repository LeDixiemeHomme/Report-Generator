package fr.valle.report_generator
package UI.facade

import domain.model.ReceptionReportData
import domain.model.ReceptionReportData.ReceptionReportDataProcessor
import domain.parser.tototoshiCSVparser.TototoshiCsvFileParser
import domain.parser.tototoshiCSVparser.objectparsers.ReceptionReportDataTototoshiParser
import logging.LogsKeeper
import services.filling.{FillingDocxToDocxService, FillingResult, FillingServiceTrait}
import services.parsing.{ParsingCsvService, ParsingResult, ParsingServiceTrait}
import services.processing.{ProcessingDataService, ProcessingResult, ProcessingServiceTrait}

import org.apache.logging.log4j.scala.Logging

class GenerateReceptionReportFeature extends Logging {

  private val parsingReceptionReportDataCsvService: ParsingServiceTrait[ReceptionReportData] = ParsingCsvService(TototoshiCsvFileParser())
  private val processingReceptionReportDataService: ProcessingServiceTrait[ReceptionReportData] = ProcessingDataService()
  private val fillingService: FillingServiceTrait = FillingDocxToDocxService()

  def action(dataPathTemp: String, templatePathTemp: String, outputPathTemp: String, outputFileName: String): Unit = {

    val parsingResult: ParsingResult[ReceptionReportData] = parsingReceptionReportDataCsvService.parse(
      filePath = dataPathTemp
    )(ReceptionReportDataTototoshiParser())

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.DEBUG, parsingResult.toString, classFrom = getClass)

    val processingResult: ProcessingResult = processingReceptionReportDataService.process(
      dataToProcess = parsingResult.parsedData.head
    )(ReceptionReportDataProcessor)

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.DEBUG, processingResult.toString, classFrom = getClass)

    val fillingResult: FillingResult = fillingService.fill(
      templateFilePath = templatePathTemp,
      valuesMap = processingResult.processedData,
      outputFilePath = outputPathTemp,
      fileName = if (outputFileName.equals("")) Some("default-value") else Some(outputFileName)
    )

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.DEBUG, fillingResult.toString, classFrom = getClass)
  }
}

object GenerateReceptionReportFeature {
  def apply(): GenerateReceptionReportFeature = new GenerateReceptionReportFeature()
}

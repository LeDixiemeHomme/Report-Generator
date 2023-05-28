package fr.valle.report_generator
package features

import domain.model.ReceptionReportData
import domain.model.ReceptionReportData.ReceptionReportDataProcessor
import domain.parser.tototoshiCSVparser.TototoshiCsvFileParser
import domain.parser.tototoshiCSVparser.objectparsers.ReceptionReportDataTototoshiParser
import features.results.{FillingResult, GenerateReceptionReportFeatureResult, ParsingResult, ProcessingResult}
import features.services.filling.{FillingDocxToDocxService, FillingServiceTrait}
import features.services.parsing.{ParsingCsvService, ParsingServiceTrait}
import features.services.processing.{ProcessingDataService, ProcessingServiceTrait}
import logging.LogsKeeper

import org.apache.logging.log4j.scala.Logging

class GenerateReceptionReportFeature extends Logging {

  private val parsingReceptionReportDataCsvService: ParsingServiceTrait[ReceptionReportData] = ParsingCsvService(TototoshiCsvFileParser())
  private val processingReceptionReportDataService: ProcessingServiceTrait[ReceptionReportData] = ProcessingDataService()
  private val fillingService: FillingServiceTrait = FillingDocxToDocxService()

  def action(dataPathTemp: String, templatePathTemp: String, outputPathTemp: String, outputFileName: String): GenerateReceptionReportFeatureResult = {

    val parsingResult: ParsingResult[ReceptionReportData] = parsingReceptionReportDataCsvService.parse(
      filePath = dataPathTemp
    )(ReceptionReportDataTototoshiParser())

    if (parsingResult.parsedData.isEmpty) return GenerateReceptionReportFeatureResult(
      isSuccess = false,
      popUpMessage = "parsingResult.parsedData.isEmpty",
      fileLocation = None
    )

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.DEBUG, parsingResult.toString, classFrom = getClass)

    val processingResult: ProcessingResult = processingReceptionReportDataService.process(
      dataToProcess = parsingResult.parsedData.head
    )(ReceptionReportDataProcessor)

    if (processingResult.processedData.isEmpty) return GenerateReceptionReportFeatureResult(
      isSuccess = false,
      popUpMessage = "processingResult.processedData.isEmpty",
      fileLocation = None
    )

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.DEBUG, processingResult.toString, classFrom = getClass)

    val fillingResult: FillingResult = fillingService.fill(
      templateFilePath = templatePathTemp,
      valuesMap = processingResult.processedData,
      outputFilePath = outputPathTemp,
      fileName = if (outputFileName.equals("")) Some("default-value") else Some(outputFileName)
    )

    if (!fillingResult.isSuccess) return GenerateReceptionReportFeatureResult(
      isSuccess = false,
      popUpMessage = fillingResult.completionMessage,
      fileLocation = None
    )

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.DEBUG, fillingResult.toString, classFrom = getClass)

    GenerateReceptionReportFeatureResult(
      isSuccess = true,
      popUpMessage = "Successfully generated",
      fileLocation = Some("location")
    )
  }
}

object GenerateReceptionReportFeature {
  def apply(): GenerateReceptionReportFeature = new GenerateReceptionReportFeature()
}

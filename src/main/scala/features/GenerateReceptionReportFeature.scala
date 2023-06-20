package fr.valle.report_generator
package features

import app.path.{Extensions, FileName, FilePath}
import customexceptions.WrongFileFormatException
import domain.csv.parser.tototoshiCSVparser.TototoshiCsvFileParser
import domain.csv.parser.tototoshiCSVparser.objectparsers.ReceptionReportDataTototoshiParser
import domain.model.ReceptionReportData
import domain.model.ReceptionReportData.ReceptionReportDataProcessor
import features.results.{FillingResult, GenerateReceptionReportFeatureResult, ParsingResult, ProcessingResult}
import features.services.filling.{FillingDocxToDocxService, FillingServiceTrait}
import features.services.parsing.{ParsingCsvService, ParsingServiceTrait}
import features.services.processing.{ProcessingDataService, ProcessingServiceTrait}
import logging.{Levels, Log, LogsKeeper}

import org.apache.logging.log4j.scala.Logging

class GenerateReceptionReportFeature extends Logging {

  private val parsingReceptionReportDataCsvService: ParsingServiceTrait[ReceptionReportData] = ParsingCsvService(TototoshiCsvFileParser())
  private val processingReceptionReportDataService: ProcessingServiceTrait[ReceptionReportData] = ProcessingDataService()
  private val fillingService: FillingServiceTrait = FillingDocxToDocxService()

  def action(dataPathTemp: String, templatePathTemp: String, outputPathTemp: String, outputFileName: String): GenerateReceptionReportFeatureResult = {

    var checkedDataFilePath: FilePath = FilePath(basePath = "", fileName = FileName(value = "temp"), extension = Extensions.csv)
    var checkedTemplateFilePath: FilePath = FilePath(basePath = "", fileName = FileName(value = "temp"), extension = Extensions.docx)

    try {
      checkedDataFilePath = FilePath.stringToFilePath(stringValue = dataPathTemp)
      checkedTemplateFilePath = FilePath.stringToFilePath(stringValue = templatePathTemp)
    } catch {
      case wrongFileFormatException: WrongFileFormatException => return GenerateReceptionReportFeatureResult(
        isSuccess = false,
        popUpMessage = wrongFileFormatException.getMessage,
        fileLocationPath = None
      )
    }

    val parsingResult: ParsingResult[ReceptionReportData] = parsingReceptionReportDataCsvService.parse(
      filePath = checkedDataFilePath
    )(ReceptionReportDataTototoshiParser())

    if (parsingResult.parsedData.isEmpty) return GenerateReceptionReportFeatureResult(
      isSuccess = false,
      popUpMessage = parsingResult.popUpMessage,
      fileLocationPath = None
    )

    LogsKeeper.keepAndLog(extLogger = logger, log = Log(message = parsingResult.toString, level = Levels.DEBUG), classFrom = getClass)

    val processingResult: ProcessingResult = processingReceptionReportDataService.process(
      dataToProcess = parsingResult.parsedData.head
    )(ReceptionReportDataProcessor)

    if (processingResult.processedData.isEmpty) return GenerateReceptionReportFeatureResult(
      isSuccess = false,
      popUpMessage = parsingResult.popUpMessage,
      fileLocationPath = None
    )

    LogsKeeper.keepAndLog(extLogger = logger, log = Log(message = processingResult.toString, level = Levels.DEBUG), classFrom = getClass)

    val outputFilePath = if (outputFileName.equals(""))
      FilePath(basePath = outputPathTemp, fileName = FileName(value = parsingResult.parsedData.head.createFileName), extension = Extensions.docx)
    else
      FilePath(basePath = outputPathTemp, fileName = FileName(value = outputFileName), extension = Extensions.docx)

    val fillingResult: FillingResult = fillingService.fill(
      templateFilePath = checkedTemplateFilePath,
      valuesMap = processingResult.processedData,
      outputFilePath = outputFilePath
    )

    if (!fillingResult.isSuccess) return GenerateReceptionReportFeatureResult(
      isSuccess = false,
      popUpMessage = fillingResult.popUpMessage,
      fileLocationPath = None
    )

    LogsKeeper.keepAndLog(extLogger = logger, log = Log(message = fillingResult.toString, level = Levels.DEBUG), classFrom = getClass)

    GenerateReceptionReportFeatureResult(
      isSuccess = true,
      popUpMessage = "Rapport de réception généré avec succès.",
      fileLocationPath = fillingResult.filledDocRelativePath
    )
  }
}

object GenerateReceptionReportFeature {
  def apply(): GenerateReceptionReportFeature = new GenerateReceptionReportFeature()
}

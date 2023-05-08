package fr.valle.report_generator
package UI.sections.pagesection.pages

import UI.DebugBorder.DEBUG_MODE
import UI.sections.pagesection.pagecontent.form.FormReport
import UI.sections.pagesection.pagecontent.form.formsections.browsebuttonstrategypattern.stategies.{BrowseDirectoryButtonStrategy, BrowseFileButtonStrategy, NoneBrowseButtonStrategy}
import UI.sections.pagesection.pagecontent.form.formsections.{IsAFormSectionTrait, LabelTextFieldBrowseFormSection, SubmitButtonFormSection}
import domain.model.ReceptionReportData
import domain.model.ReceptionReportData.{ReceptionReportDataParser, ReceptionReportDataProcessor}
import logging.LogsKeeper
import services.filling.{FillingDocxToDocxService, FillingResult, FillingServiceTrait}
import services.parsing.{ParsingCsvService, ParsingResult, ParsingServiceTrait}
import services.processing.{ProcessingDataService, ProcessingResult, ProcessingServiceTrait}

import org.apache.logging.log4j.scala.Logging
import scalafx.scene.layout._

class ReceptionReportFormPage extends Logging with IsAPageTrait {

  private val parsingReceptionReportDataCsvService: ParsingServiceTrait[ReceptionReportData] = ParsingCsvService()
  private val processingReceptionReportDataService: ProcessingServiceTrait[ReceptionReportData] = ProcessingDataService()
  private val fillingService: FillingServiceTrait = FillingDocxToDocxService()

  private val dataFilePathFormSection: IsAFormSectionTrait = LabelTextFieldBrowseFormSection(
    label = "Fichier de données (Excel) :",
    example = "..\\données.csv",
    required = true,
    browseStrategy = BrowseFileButtonStrategy
  )

  private val templateFilePathFormSection: IsAFormSectionTrait = LabelTextFieldBrowseFormSection(
    label = "Fichier modèle (Word) :",
    example = "..\\rapport-template.docx",
    required = true,
    browseStrategy = BrowseFileButtonStrategy
  )

  private val outputDirectoryFormSection: IsAFormSectionTrait = LabelTextFieldBrowseFormSection(
    label = "Dossier cible :",
    example = "..\\dossier-rapports-complets",
    required = true,
    browseStrategy = BrowseDirectoryButtonStrategy
  )

  private val outputFileNameFormSection: IsAFormSectionTrait = LabelTextFieldBrowseFormSection(
    label = "Nom du fichier à créer :",
    example = "rapport-loreal-15.docx",
    required = false,
    browseStrategy = NoneBrowseButtonStrategy
  )

  private val submitButton = SubmitButtonFormSection()

  if (DEBUG_MODE) submitButton.myButton.disable = false

  submitButton.myButton.onAction = _ => {
    var dataPathTemp: String = dataFilePathFormSection.myTextField.getText
    var templatePathTemp: String = templateFilePathFormSection.myTextField.getText
    var outputPathTemp: String = outputDirectoryFormSection.myTextField.getText

    if (DEBUG_MODE) {
      dataPathTemp = "C:\\Users\\benoi\\Dev\\Projects\\Report-Generator\\src\\main\\resources\\inputs\\data\\reception-report-data-v1-2.csv"
      templatePathTemp = "C:\\Users\\benoi\\Dev\\Projects\\Report-Generator\\src\\main\\resources\\inputs\\templates\\template-report-data-v1-3-mini.docx"
      outputPathTemp = "C:\\Users\\benoi\\Dev\\Projects\\Report-Generator\\outputs\\"
    }

    val parsingResult: ParsingResult[ReceptionReportData] = parsingReceptionReportDataCsvService.parse(
      filePath = dataPathTemp
    )(ReceptionReportDataParser)

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.DEBUG, parsingResult.toString, classFrom = getClass)

    val processingResult: ProcessingResult = processingReceptionReportDataService.process(
      dataToProcess = parsingResult.parsedData.head
    )(ReceptionReportDataProcessor)

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.DEBUG, processingResult.toString, classFrom = getClass)

    val fillingResult: FillingResult = fillingService.fill(
      templateFilePath = templatePathTemp,
      valuesMap = processingResult.processedData,
      outputFilePath = outputPathTemp,
      fileName = if (outputFileNameFormSection.myTextField.getText.equals("")) Some("default-value") else Some(outputFileNameFormSection.myTextField.getText)
    )

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.DEBUG, fillingResult.toString, classFrom = getClass)
  }

  val fields: List[IsAFormSectionTrait] = List(
    dataFilePathFormSection,
    templateFilePathFormSection,
    outputDirectoryFormSection,
    outputFileNameFormSection
  )

  val body: VBox = new VBox {
    id = myPageID
    children = new FormReport(forms = fields, submitButton = submitButton).myForm
  }

  override def myPage: APage = APage(body = body)

  override def myPageID: String = ReceptionReportFormPage.RECEPTION_REPORT_DATA_FORM_PAGE_ID

  override def myPageName: String = "Rapport de Réception"
}

object ReceptionReportFormPage {
  def apply(): ReceptionReportFormPage = new ReceptionReportFormPage()

  final val RECEPTION_REPORT_DATA_FORM_PAGE_ID: String = "receptionReport"
}
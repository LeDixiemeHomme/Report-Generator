package fr.valle.report_generator
package UI.sections.pagesection.pages

import UI.DebugBorder.DEBUG_MODE
import UI.sections.pagesection.pagecontent.form.FormReport
import UI.sections.pagesection.pagecontent.form.formsections.browsebuttonstrategypattern.stategies.{BrowseDirectoryButtonStrategy, BrowseFileButtonStrategy, NoneBrowseButtonStrategy}
import UI.sections.pagesection.pagecontent.form.formsections.{FormSectionTrait, LabelTextFieldBrowseFormSection, SubmitButtonFormSection}
import domain.model.InterventionData
import domain.model.InterventionData.{InterventionDataParser, InterventionDataProcessor}
import logging.LogsKeeper
import services.filling.{FillingDocxToDocxService, FillingResult, FillingServiceTrait}
import services.parsing.{ParsingCsvService, ParsingResult, ParsingServiceTrait}
import services.processing.{ProcessingDataService, ProcessingResult, ProcessingServiceTrait}

import org.apache.logging.log4j.scala.Logging
import scalafx.scene.layout._

class InterventionDataFormPage extends Logging with IsAPageTrait {

  private val fillingService: FillingServiceTrait = FillingDocxToDocxService()
  private val parsingInterventionDataCsvService: ParsingServiceTrait[InterventionData] = ParsingCsvService()
  private val processingInterventionDataService: ProcessingServiceTrait[InterventionData] = ProcessingDataService()

  private val dataFilePathFormSection: FormSectionTrait = new LabelTextFieldBrowseFormSection(
    label = "Fichier de données (Excel) :",
    example = "../données.csv",
    required = true,
    browseStrategy = BrowseFileButtonStrategy
  )

  private val templateFilePathFormSection: FormSectionTrait = new LabelTextFieldBrowseFormSection(
    label = "Fichier modèle (Word) :",
    example = "../rapport-template.docx",
    required = true,
    browseStrategy = BrowseFileButtonStrategy
  )

  private val outputDirectoryFormSection: FormSectionTrait = new LabelTextFieldBrowseFormSection(
    label = "Dossier cible :",
    example = "../dossier-rapports-complets/",
    required = true,
    browseStrategy = BrowseDirectoryButtonStrategy
  )

  private val outputFileNameFormSection: FormSectionTrait = new LabelTextFieldBrowseFormSection(
    label = "Nom du fichier à créer :",
    example = "rapport-loreal-15.docx",
    required = false,
    browseStrategy = NoneBrowseButtonStrategy
  )

  private val submitButton: SubmitButtonFormSection = new SubmitButtonFormSection()

  if (DEBUG_MODE)
    submitButton.myButton.disable = false

  submitButton.myButton.onAction = _ => {
    var dataPathTemp: String = dataFilePathFormSection.myTextField.getText
    var templatePathTemp: String = templateFilePathFormSection.myTextField.getText
    var outputPathTemp: String = outputDirectoryFormSection.myTextField.getText

    if (DEBUG_MODE) {
      dataPathTemp = "C:\\Users\\benoi\\Dev\\Projects\\Report-Generator\\src\\main\\resources\\inputs\\data\\intervention-data.csv"
      templatePathTemp = "C:\\Users\\benoi\\Dev\\Projects\\Report-Generator\\src\\main\\resources\\inputs\\templates\\AFC Delagrave avec balise.docx"
      outputPathTemp = "C:\\Users\\benoi\\Dev\\Projects\\Report-Generator\\outputs\\"
    }

    val parsingResult: ParsingResult[InterventionData] = parsingInterventionDataCsvService.parse(
      filePath = dataPathTemp
    )(InterventionDataParser)

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.DEBUG, parsingResult.toString, classFrom = getClass)

    val processingResult: ProcessingResult = processingInterventionDataService.process(
      dataToProcess = parsingResult.parsedData(0)
    )(InterventionDataProcessor)

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.DEBUG, processingResult.toString, classFrom = getClass)

    val fillingResult: FillingResult = fillingService.fill(
      templateFilePath = templatePathTemp,
      valuesMap = processingResult.processedData,
      outputFilePath = outputPathTemp,
      fileName = if (outputFileNameFormSection.myTextField.getText.equals("")) Some("default-value") else Some(outputFileNameFormSection.myTextField.getText)
    )

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.DEBUG, fillingResult.toString, classFrom = getClass)
  }

  val fields: List[FormSectionTrait] = List(
    dataFilePathFormSection,
    templateFilePathFormSection,
    outputDirectoryFormSection,
    outputFileNameFormSection
  )

  val body: VBox = new VBox {
    id = myPageID
    children = new FormReport(forms = fields, submitButton = submitButton).myForm
  }

  override def myPage: Page = Page(body = body)

  override def myPageID: String = InterventionDataFormPage.INTERVENTION_DATA_FORM_PAGE_ID

  override def myPageName: String = "Générer un rapport"
}

object InterventionDataFormPage {
  def apply(): InterventionDataFormPage = new InterventionDataFormPage()

  final val INTERVENTION_DATA_FORM_PAGE_ID: String = "PageOne"
}

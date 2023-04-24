package fr.valle.report_generator
package UI.sections.pagesection.pages

import UI.sections.pagesection.pagecontent.form.FormReport
import UI.sections.pagesection.pagecontent.form.formsections.browsebuttonstrategypattern.stategies.{BrowseDirectoryButtonStrategy, BrowseFileButtonStrategy, NoneBrowseButtonStrategy}
import UI.sections.pagesection.pagecontent.form.formsections.{FormSectionTrait, LabelTextFieldBrowseFormSection, SubmitButtonFormSection}
import domain.model.InterventionData
import domain.model.InterventionData.{InterventionDataParser, InterventionDataProcessor}
import services.filling.{FillingDocxToDocxService, FillingResult, FillingServiceTrait}
import services.parsing.{ParsingCsvService, ParsingResult, ParsingServiceTrait}
import services.processing.{ProcessingDataService, ProcessingResult, ProcessingServiceTrait}

import scalafx.scene.control.TextField
import scalafx.scene.layout._

class InterventionDataFormPage extends IsAPageTrait {

  private val fillingService: FillingServiceTrait = FillingDocxToDocxService()
  private val parsingInterventionDataCsvService: ParsingServiceTrait[InterventionData] = ParsingCsvService()
  private val processingInterventionDataService: ProcessingServiceTrait[InterventionData] = ProcessingDataService()

  private val dataFilePathTextField: TextField = new TextField()
  private val templateFilePathTextField: TextField = new TextField()
  private val outputDirectoryTextField: TextField = new TextField()
  private val outputFileNameTextField: TextField = new TextField()

  private val dataFilePathFormSection: FormSectionTrait = new LabelTextFieldBrowseFormSection(
    label = "Fichier de données (Excel) :",
    myTextField = dataFilePathTextField,
    required = true,
    browseStrategy = BrowseFileButtonStrategy
  )

  private val templateFilePathFormSection: FormSectionTrait = new LabelTextFieldBrowseFormSection(
    label = "Fichier modèle (Word) :",
    myTextField = templateFilePathTextField,
    required = true,
    browseStrategy = BrowseFileButtonStrategy
  )

  private val outputDirectoryFormSection: FormSectionTrait = new LabelTextFieldBrowseFormSection(
    label = "Dossier cible :",
    myTextField = outputDirectoryTextField,
    required = true,
    browseStrategy = BrowseDirectoryButtonStrategy
  )

  private val outputFileNameFormSection: FormSectionTrait = new LabelTextFieldBrowseFormSection(
    label = "Nom du fichier à créer :",
    myTextField = outputFileNameTextField,
    required = false,
    browseStrategy = NoneBrowseButtonStrategy
  )

  private val submitButton: SubmitButtonFormSection = new SubmitButtonFormSection()

  submitButton.myButton.onAction = _ => {
    val parsingResult: ParsingResult[InterventionData] = parsingInterventionDataCsvService.parse(
      filePath = dataFilePathTextField.getText
    )(InterventionDataParser)

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.DEBUG, parsingResult.toString, classFrom = getClass)

    val processingResult: ProcessingResult = processingInterventionDataService.process(
      dataToProcess = parsingResult.parsedData(0)
    )(InterventionDataProcessor)

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.DEBUG, processingResult.toString, classFrom = getClass)

    val fillignResult: FillingResult = fillingService.fill(
      templateFilePath = templateFilePathTextField.getText,
      valuesMap = processingResult.processedData,
      outputFilePath = outputDirectoryTextField.getText,
      fileName = Some(outputFileNameTextField.getText)
    )

    LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.DEBUG, fillignResult.toString, classFrom = getClass)
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

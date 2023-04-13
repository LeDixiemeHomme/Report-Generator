package fr.valle.report_generator
package UI.sections.pagesection.pages

import UI.sections.formsection.forms.{LabelTextFieldBrowseFormSection, LabelTextFieldFormSection, SubmitButtonFormSection}
import UI.sections.formsection.{FormSection, FormSectionTrait}
import domain.model.InterventionData
import domain.model.InterventionData.InterventionDataParser
import services.filling.{FillingDocxToDocxService, FillingResult, FillingServiceTrait}
import services.parsing.{ParsingCsvService, ParsingResult, ParsingServiceTrait}
import services.processing.{ProcessingCarDataService, ProcessingResult, ProcessingServiceTrait}

import scalafx.scene.control.TextField
import scalafx.scene.layout._

class PageOne extends IsAPageTrait {

  private val fillingService: FillingServiceTrait = FillingDocxToDocxService()
  private val parsingInterventionDataCsvService: ParsingServiceTrait[InterventionData] = ParsingCsvService()
  private val processingCarDataService: ProcessingServiceTrait = ProcessingCarDataService()

  private val dataFilePathTextField: TextField = new TextField()
  private val templateFilePathTextField: TextField = new TextField()
  private val outputDirectoryTextField: TextField = new TextField()
  private val outputFileNameTextField: TextField = new TextField()

  private val dataFilePathFormSection: FormSectionTrait = new LabelTextFieldBrowseFormSection(
    label = "* Fichier de données (Excel) :",
    myTextField = dataFilePathTextField,
    required = true
  )

  private val templateFilePathFormSection: FormSectionTrait = new LabelTextFieldBrowseFormSection(
    label = "* Fichier modèle (Word) :",
    myTextField = templateFilePathTextField,
    required = true
  )

  //todo see if it is possible to browse a dir instead of a file
  private val outputDirectoryFormSection: FormSectionTrait = new LabelTextFieldBrowseFormSection(
    label = "* Dossier cible :",
    myTextField = outputDirectoryTextField,
    required = true
  )

  private val outputFileNameFormSection: FormSectionTrait = new LabelTextFieldFormSection(
    label = "Nom du fichier à créer:",
    myTextField = outputFileNameTextField,
    required = false
  )

  private val submitButton: SubmitButtonFormSection = new SubmitButtonFormSection()

  submitButton.myButton.onAction = _ => {
    val parsingResult: ParsingResult[InterventionData] = parsingInterventionDataCsvService.parse(
      filePath = dataFilePathTextField.getText
    )(InterventionDataParser)

    println(parsingResult)

    val processingResult: ProcessingResult = processingCarDataService.process(
      dataToProcess = parsingResult.parsedData
    )

    println(processingResult)

    val result: FillingResult = fillingService.fill(
      templateFilePath = templateFilePathTextField.getText,
      valuesMap = processingResult.processedData,
      outputFilePath = outputDirectoryTextField.getText,
      fileName = Some(outputFileNameTextField.getText)
    )

    println(result)
  }

  val fields: List[FormSectionTrait] = List(
    dataFilePathFormSection,
    templateFilePathFormSection,
    outputDirectoryFormSection,
    outputFileNameFormSection
  )

  val body: VBox = new FormSection(forms = fields, submitButton = submitButton).myForm

  override def myPage: Page = Page(body = body)
}

object PageOne {
  def apply(): PageOne = new PageOne()
}

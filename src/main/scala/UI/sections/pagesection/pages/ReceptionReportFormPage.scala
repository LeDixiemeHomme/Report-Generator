package fr.valle.report_generator
package UI.sections.pagesection.pages

import UI.DebugBorder.DEBUG_MODE
import UI.sections.pagesection.pagecontent.form.FormReport
import UI.sections.pagesection.pagecontent.form.formsections.browsebuttonstrategypattern.stategies.{BrowseDirectoryButtonStrategy, BrowseFileButtonStrategy, NoneBrowseButtonStrategy}
import UI.sections.pagesection.pagecontent.form.formsections.{IsAFormSectionTrait, LabelTextFieldBrowseFormSection, SubmitButtonFormSection}
import features.GenerateReceptionReportFeature
import features.results.GenerateReceptionReportFeatureResult

import org.apache.logging.log4j.scala.Logging
import scalafx.scene.layout._

class ReceptionReportFormPage extends Logging with IsAPageTrait {

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

  var dataPathTemp: String = dataFilePathFormSection.myTextField.getText
  var templatePathTemp: String = templateFilePathFormSection.myTextField.getText
  var outputPathTemp: String = outputDirectoryFormSection.myTextField.getText

  if (DEBUG_MODE) {
    //    dataPathTemp = "C:\\Users\\benoi\\Dev\\Projects\\Report-Generator\\src\\test\\resources\\reception-report-data-test-without-row-data.csv"
    //    dataPathTemp = "C:\\Users\\benoi\\Dev\\Projects\\Report-Generator\\src\\test\\resources\\reception-report-data-test-random-colomn-order.csv"
    //    dataPathTemp = "C:\\Users\\benoi\\Dev\\Projects\\Report-Generator\\src\\test\\resources\\reception-report-data-test-missing-values.csv"
    //    dataPathTemp = "C:\\Users\\benoi\\Dev\\Projects\\Report-Generator\\src\\test\\resources\\reception-report-data-test-missing-column.csv"
    //    dataPathTemp = "C:\\Users\\benoi\\Dev\\Projects\\Report-Generator\\src\\test\\resources\\reception-report-data-test-empty.csv"
    dataPathTemp = "C:\\Users\\benoi\\Dev\\Projects\\Report-Generator\\src\\test\\resources\\reception-report-data-test.csv"
    //    templatePathTemp = "C:\\Users\\benoi\\Dev\\Projects\\Report-Generator\\src\\main\\resources\\inputs\\templates\\template-report-data-v1-3-mini.docx"
    templatePathTemp = "C:\\Users\\benoi\\Dev\\Projects\\Report-Generator\\src\\main\\resources\\inputs\\templates\\template-test-empty.docx"
    outputPathTemp = "C:\\Users\\benoi\\Dev\\Projects\\Report-Generator\\outputs\\"
  }

  submitButton.myButton.onAction = _ => {
    val result: GenerateReceptionReportFeatureResult = GenerateReceptionReportFeature().action(
      dataPathTemp = dataPathTemp,
      templatePathTemp = templatePathTemp,
      outputPathTemp = outputPathTemp,
      outputFileName = outputFileNameFormSection.myTextField.getText
    )
    println(result.popUpMessage)
    println(result.isSuccess)
    println(result.fileLocation.getOrElse("empty"))
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

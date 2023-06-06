package fr.valle.report_generator
package UI.sections.pagesection.pages

import UI.DebugBorder.DEBUG_MODE
import UI.sections.pagesection.pagecontent.form.FormReport
import UI.sections.pagesection.pagecontent.form.formsections.browsebuttonstrategypattern.stategies.{BrowseDirectoryButtonStrategy, BrowseFileButtonStrategy, NoneBrowseButtonStrategy}
import UI.sections.pagesection.pagecontent.form.formsections.{IsAFormSectionTrait, LabelTextFieldBrowseFormSection, SubmitButtonFormSection}
import UI.stages.IsAStageTrait
import UI.stages.popupstages.PopupStage
import domain.path.FilePath
import features.GenerateReceptionReportFeature
import features.results.GenerateReceptionReportFeatureResult

import org.apache.logging.log4j.scala.Logging
import scalafx.scene.layout._

import java.nio.file.Paths

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

  submitButton.myButton.onAction = _ => {

    var dataPathTemp: String = dataFilePathFormSection.myTextField.getText
    var templatePathTemp: String = templateFilePathFormSection.myTextField.getText
    var outputPathTemp: String = outputDirectoryFormSection.myTextField.getText

    if (DEBUG_MODE) {
      val resourcePath = "src/test/resources"

      //    val dataPathTempValue = "reception-report-data-test-without-row-data.csv"
      //    val dataPathTempValue = "reception-report-data-test-random-colomn-order.csv"
      //    val dataPathTempValue = "reception-report-data-test-missing-values.csv"
      //    val dataPathTempValue = "reception-report-data-test-missing-column.csv"
      //    val dataPathTempValue = "reception-report-data-test-empty.csv"
      val dataPathTempValue = "reception-report-data-test.csv"

      //    val templatePathTempValue = "template-test-empty.docx"
      val templatePathTempValue = "template-test.docx"

      dataPathTemp = Paths.get(resourcePath, dataPathTempValue).toString

      templatePathTemp = Paths.get(resourcePath, templatePathTempValue).toString

      outputPathTemp = Paths.get("").toAbsolutePath.toString
    }


    val result: GenerateReceptionReportFeatureResult = GenerateReceptionReportFeature().action(
      dataPathTemp = dataPathTemp,
      templatePathTemp = templatePathTemp,
      outputPathTemp = outputPathTemp,
      outputFileName = outputFileNameFormSection.myTextField.getText
    )

    val popupStage: IsAStageTrait = PopupStage(
      popupMessage = result.popUpMessage,
      fileLocation = result.fileLocationPath.getOrElse("").asInstanceOf[FilePath].constructFinalPath,
      isSuccess = result.isSuccess
    )

    popupStage.showMyStage()
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

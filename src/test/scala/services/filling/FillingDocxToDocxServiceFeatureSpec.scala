package fr.valle.report_generator
package services.filling

import domain.path.{FilePath, TestFilePathProvider}
import features.services.filling.FillingDocxToDocxService

import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.scalatest.featurespec.AnyFeatureSpecLike
import org.scalatest.matchers.should.Matchers
import org.scalatest.{BeforeAndAfterEach, GivenWhenThen}

import java.io.{File, FileInputStream, FileOutputStream}

class FillingDocxToDocxServiceFeatureSpec extends AnyFeatureSpecLike with GivenWhenThen with BeforeAndAfterEach with Matchers {
  var fillingDocxToDocxService: FillingDocxToDocxService = _

  val outputFilePath: String = getClass.getResource("/").getPath
  val generatedTemplateFileName = "template-test-filling-service-generated"
  val fileName = "a-filled-doc"
  val docxFileExtension = ".docx"
  val generatedTemplateFileRelativePathOthers: String = outputFilePath + generatedTemplateFileName + docxFileExtension
  val generatedTemplateFileRelativePathWindows: String = FilePath.stringToFilePath(generatedTemplateFileRelativePathOthers).constructFinalPath

  var document: XWPFDocument = _

  override def beforeEach(): Unit = {
    fillingDocxToDocxService = FillingDocxToDocxService()

    document = new XWPFDocument()
    val paragraph = document.createParagraph()
    val run = paragraph.createRun()
    run.setText("#nombre_sorbonne#;#mois#;#annee#;#nom_etablissement#;#ville#;#departement#;#adresse#;#code_postal#;#jour#;#intervenant#;#sexe#;#societe_soutraite#;#nom1#;#nom2#;#numero_d_affaire#")

    document.write(new FileOutputStream(new File(generatedTemplateFileRelativePathOthers)))
    document.close()
  }

  Feature("Using FillingDocxToDocxService fill method") {

    Scenario("Using the path of the template and a map value to create a filled docx") {
      val expectedDocxContentAfterFilling: String = "2;Janvier;2023;Université Sorbonne Paris Nord;Villetaneuse;Seine-Saint-Denis;1 Rue de la Croix Faron;93430;15;John Doe;M;FabCorp;Robert;Dupont;ABC123"

      Given("the templateFilePath, a valuesMap, an outputFilePath, a fileName")
      val templateFilePath = generatedTemplateFileRelativePathOthers
      val valuesMap: Map[String, String] = TestDataProvider.provideReceptionReportData_1MapValues

      When("using the FillingDocxToDocxService.fill method")
      val fillingResult = fillingDocxToDocxService.fill(templateFilePath = FilePath.stringToFilePath(templateFilePath), valuesMap = valuesMap,
        outputFilePath = FilePath.stringToFilePath(generatedTemplateFileRelativePathOthers))

      val paragraphFromTheFilledDoc = new XWPFDocument(new FileInputStream(new File(fillingResult.filledDocRelativePath.get.constructFinalPath))).getParagraphs.get(0).getText()

      Then("the result should be correct")
      TestFilePathProvider.assertByOs(
        expectedWindows = fillingResult.popUpMessage, actualWindows = s"Successfully written $generatedTemplateFileName$docxFileExtension in ${FilePath.stringToFilePath(s"${outputFilePath}test.docx").basePath}",
        expectedOthers = fillingResult.popUpMessage, actualOthers = s"Successfully written $generatedTemplateFileName$docxFileExtension in ${FilePath.stringToFilePath(s"${outputFilePath}test.docx").basePath}"
      )
      TestFilePathProvider.assertByOs(
        expectedWindows = fillingResult.filledDocRelativePath.get.constructFinalPath, actualWindows = generatedTemplateFileRelativePathWindows,
        expectedOthers = fillingResult.filledDocRelativePath.get.constructFinalPath, actualOthers = generatedTemplateFileRelativePathOthers
      )
      TestFilePathProvider.assertByOs(
        expectedWindows = paragraphFromTheFilledDoc, actualWindows = expectedDocxContentAfterFilling,
        expectedOthers = paragraphFromTheFilledDoc, actualOthers = expectedDocxContentAfterFilling
      )
    }
  }
}

package fr.valle.report_generator
package services.filling

import features.services.filling.FillingDocxToDocxService

import fr.valle.report_generator.domain.path.FilePath
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
  val generatedTemplateFileRelativePath: String = outputFilePath + generatedTemplateFileName + docxFileExtension

  var document: XWPFDocument = _

  override def beforeEach(): Unit = {
    fillingDocxToDocxService = FillingDocxToDocxService()

    document = new XWPFDocument()
    val paragraph = document.createParagraph()
    val run = paragraph.createRun()
    run.setText("#nombre_sorbonne#;#mois#;#annee#;#nom_etablissement#;#ville#;#departement#;#adresse#;#code_postal#;#jour#;#intervenant#;#sexe#;#societe_soutraite#;#nom1#;#nom2#;#numero_d_affaire#")

    document.write(new FileOutputStream(new File(generatedTemplateFileRelativePath)))
    document.close()
  }

  Feature("Using FillingDocxToDocxService fill method") {

    Scenario("Using the path of the template and a map value to create a filled docx") {
      val expectedDocxContentAfterFilling: String = "2;Janvier;2023;Université Sorbonne Paris Nord;Villetaneuse;Seine-Saint-Denis;1 Rue de la Croix Faron;93430;15;John Doe;M;FabCorp;Robert;Dupont;ABC123"

      Given("the templateFilePath, a valuesMap, an outputFilePath, a fileName")
      val templateFilePath = generatedTemplateFileRelativePath
      val valuesMap: Map[String, String] = TestDataProvider.provideReceptionReportData_1MapValues

      When("using the FillingDocxToDocxService.fill method")
      val fillingResult = fillingDocxToDocxService.fill(templateFilePath = FilePath.stringToFilePath(templateFilePath), valuesMap = valuesMap,
        outputFilePath = FilePath.stringToFilePath(generatedTemplateFileRelativePath))

      val paragraphFromTheFilledDoc = new XWPFDocument(new FileInputStream(new File(fillingResult.filledDocRelativePath.get.constructFinalPath))).getParagraphs.get(0).getText()

      Then("the result should be correct")
      fillingResult.popUpMessage shouldEqual s"Successfully written $generatedTemplateFileName$docxFileExtension in $outputFilePath"
      fillingResult.filledDocRelativePath.get.constructFinalPath shouldEqual generatedTemplateFileRelativePath
      paragraphFromTheFilledDoc shouldEqual expectedDocxContentAfterFilling
    }
  }
}

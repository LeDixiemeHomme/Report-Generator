package fr.valle.report_generator
package services.filling

import domain.model.ReportDataV1

import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.scalatest.featurespec.AnyFeatureSpecLike
import org.scalatest.matchers.should.Matchers
import org.scalatest.{BeforeAndAfterEach, GivenWhenThen}

import java.io.{File, FileInputStream, FileOutputStream}

class FillingDocxToDocxServiceSpec extends AnyFeatureSpecLike with GivenWhenThen with BeforeAndAfterEach with Matchers {
  var myFillingDocxToDocxService: FillingDocxToDocxService = _

  val nombreSorbonnes1: String = "2"
  val mois1: String = "Janvier"
  val annee1: String = "2023"
  val nomEtablissement1: String = "Université Sorbonne Paris Nord"
  val ville1: String = "Villetaneuse"
  val departement1: String = "Seine-Saint-Denis"
  val adresse1: String = "1 Rue de la Croix Faron"
  val codePostal1: String = "93430"
  val jour1: String = "15"
  val intervenant1: String = "John Doe"
  val sexe1: String = "M"
  val societeSoutraite1: String = "FabCorp"
  val nom11: String = "Robert"
  val nom21: String = "Dupont"
  val numeroAffaire1: String = "ABC123"

  val outputFilePath: String = getClass.getResource("/").getPath
  val generatedTemplateFileName = "template-test-filling-service-generated"
  val fileName = "a-filled-doc"
  val docxFileExtension = ".docx"
  val generatedTemplateFileRelativePath: String = outputFilePath + generatedTemplateFileName + docxFileExtension

  var document: XWPFDocument = _

  override def beforeEach(): Unit = {
    myFillingDocxToDocxService = FillingDocxToDocxService()

    document = new XWPFDocument()
    val paragraph = document.createParagraph()
    val run = paragraph.createRun()
    run.setText("#nombre_sorbonne#;#mois#;#annee#;#nom_etablissement#;#ville#;#departement#;#adresse#;#code_postal#;#jour#;#intervenant#;#sexe#;#societe_soutraite#;#nom1#;#nom2#;#numero_d_affaire#")

    document.write(new FileOutputStream(new File(generatedTemplateFileRelativePath)))
    document.close()
  }

  Feature("Using FillingDocxToDocxService fill method") {

    Scenario("Using the path of the template and a map value to create a filled docx") {

      val reportDataV1_1: ReportDataV1 = ReportDataV1(nombreSorbonnes = nombreSorbonnes1, mois = mois1, annee = annee1,
        nomEtablissement = nomEtablissement1, ville = ville1, departement = departement1, adresse = adresse1,
        codePostal = codePostal1, jour = jour1, intervenant = intervenant1, sexe = sexe1, societeSoutraite = societeSoutraite1,
        nom1 = nom11, nom2 = nom21, numeroAffaire = numeroAffaire1)

      val expectedDocxContentAfterFilling: String = "2;Janvier;2023;Université Sorbonne Paris Nord;Villetaneuse;Seine-Saint-Denis;1 Rue de la Croix Faron;93430;15;John Doe;M;FabCorp;Robert;Dupont;ABC123"

      Given("the templateFilePath, a valuesMap, an outputFilePath, a fileName")
      val templateFilePath = generatedTemplateFileRelativePath
      val valuesMap: Map[String, String] = Map(
        "#nombre_sorbonne#" -> reportDataV1_1.nombreSorbonnes,
        "#mois#" -> reportDataV1_1.mois,
        "#annee#" -> reportDataV1_1.annee,
        "#nom_etablissement#" -> reportDataV1_1.nomEtablissement,
        "#ville#" -> reportDataV1_1.ville,
        "#departement#" -> reportDataV1_1.departement,
        "#adresse#" -> reportDataV1_1.adresse,
        "#code_postal#" -> reportDataV1_1.codePostal,
        "#jour#" -> reportDataV1_1.jour,
        "#intervenant#" -> reportDataV1_1.intervenant,
        "#sexe#" -> reportDataV1_1.sexe,
        "#societe_soutraite#" -> reportDataV1_1.societeSoutraite,
        "#nom1#" -> reportDataV1_1.nom1,
        "#nom2#" -> reportDataV1_1.nom2,
        "#numero_d_affaire#" -> reportDataV1_1.numeroAffaire
      )

      When("using the myFillingDocxToDocxService.fill method")
      val fillingResult = myFillingDocxToDocxService.fill(templateFilePath = templateFilePath, valuesMap = valuesMap,
        outputFilePath = outputFilePath, optionalFileName = Some(generatedTemplateFileName))

      val paragraphFromTheFilledDoc = new XWPFDocument(new FileInputStream(new File(fillingResult.filledDocRelativePath))).getParagraphs.get(0).getText()

      Then("the result should be correct")
      fillingResult.completionMessage shouldEqual s"Successfully written in $outputFilePath"
      fillingResult.outputFilePath shouldEqual outputFilePath
      fillingResult.filledDocRelativePath shouldEqual generatedTemplateFileRelativePath
      paragraphFromTheFilledDoc shouldEqual expectedDocxContentAfterFilling
    }
  }
}

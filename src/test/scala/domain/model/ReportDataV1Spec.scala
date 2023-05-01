package fr.valle.report_generator
package domain.model

import domain.model.ReportDataV1.{ReportDataV1Parser, ReportDataV1Processor}

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.{BeforeAndAfterEach, GivenWhenThen, PrivateMethodTester}

class ReportDataV1Spec extends AnyFlatSpec with PrivateMethodTester with GivenWhenThen with BeforeAndAfterEach with Matchers {

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

  val nombreSorbonnes2: String = "1"
  val mois2: String = "Février"
  val annee2: String = "2023"
  val nomEtablissement2: String = "Sorbonne Université"
  val ville2: String = "Paris"
  val departement2: String = "Paris"
  val adresse2: String = "4 Place Jussieu"
  val codePostal2: String = "75005"
  val jour2: String = "23"
  val intervenant2: String = "Jane Smith"
  val sexe2: String = "F"
  val societeSoutraite2: String = "CoolCorp"
  val nom12: String = "Martin"
  val nom22: String = "Chen"
  val numeroAffaire2: String = "XYZ789"

  var reportDataV1_1: ReportDataV1 = _

  override def beforeEach(): Unit = {
    reportDataV1_1 = ReportDataV1(nombreSorbonnes = nombreSorbonnes1, mois = mois1, annee = annee1,
      nomEtablissement = nomEtablissement1, ville = ville1, departement = departement1, adresse = adresse1,
      codePostal = codePostal1, jour = jour1, intervenant = intervenant1, sexe = sexe1, societeSoutraite = societeSoutraite1,
      nom1 = nom11, nom2 = nom21, numeroAffaire = numeroAffaire1)
  }

  it should "have a correct string representation" in {
    val correctStringValue: String = "ReportDataV1{Nombre sorbonnes: 2, Mois: Janvier, Année: 2023, Nom établissement: Université Sorbonne Paris Nord, Ville: Villetaneuse, Département: Seine-Saint-Denis, Adresse: 1 Rue de la Croix Faron, Code postal: 93430, Jour: 15, Intervenant: John Doe, Sexe: M, Société soutraite: FabCorp, Nom1: Robert, Nom2: Dupont, Numéro d'affaire: ABC123}"

    Given("a ReportDataV1")
    println(reportDataV1_1)

    When("using the toString method")
    val stringValue = reportDataV1_1.toString

    Then("stringValue shouldEqual correctStringValue")
    stringValue shouldEqual correctStringValue
  }

  it should "return correct values for its properties" in {

    Given("a ReportDataV1")
    println(reportDataV1_1)

    Then("all properties have the right value")
    reportDataV1_1.nombreSorbonnes shouldEqual nombreSorbonnes1
    reportDataV1_1.mois shouldEqual mois1
    reportDataV1_1.annee shouldEqual annee1
    reportDataV1_1.nomEtablissement shouldEqual nomEtablissement1
    reportDataV1_1.ville shouldEqual ville1
    reportDataV1_1.departement shouldEqual departement1
    reportDataV1_1.adresse shouldEqual adresse1
    reportDataV1_1.codePostal shouldEqual codePostal1
    reportDataV1_1.jour shouldEqual jour1
    reportDataV1_1.intervenant shouldEqual intervenant1
    reportDataV1_1.sexe shouldEqual sexe1
    reportDataV1_1.societeSoutraite shouldEqual societeSoutraite1
    reportDataV1_1.nom1 shouldEqual nom11
    reportDataV1_1.nom2 shouldEqual nom21
    reportDataV1_1.numeroAffaire shouldEqual numeroAffaire1
  }

  it should "parse a list of lists of string values into a list of ReportDataV1 objects" in {
    val list1: List[String] = nombreSorbonnes1 :: mois1 :: annee1 :: nomEtablissement1 :: ville1 :: departement1 :: adresse1 :: codePostal1 :: jour1 :: intervenant1 :: sexe1 :: societeSoutraite1 :: nom11 :: nom21 :: numeroAffaire1 :: Nil
    val list2: List[String] = nombreSorbonnes2 :: mois2 :: annee2 :: nomEtablissement2 :: ville2 :: departement2 :: adresse2 :: codePostal2 :: jour2 :: intervenant2 :: sexe2 :: societeSoutraite2 :: nom12 :: nom22 :: numeroAffaire2 :: Nil

    val reportDataV1_2: ReportDataV1 = ReportDataV1(nombreSorbonnes = nombreSorbonnes2, mois = mois2, annee = annee2,
      nomEtablissement = nomEtablissement2, ville = ville2, departement = departement2, adresse = adresse2,
      codePostal = codePostal2, jour = jour2, intervenant = intervenant2, sexe = sexe2, societeSoutraite = societeSoutraite2,
      nom1 = nom12, nom2 = nom22, numeroAffaire = numeroAffaire2)

    val correctListOfReportDataV1: List[ReportDataV1] = reportDataV1_1 :: reportDataV1_2 :: Nil

    Given("a list of lists of string values containing the properties of objects reportDataV1_1 and reportDataV1_2")
    val listOfListOfString: List[List[String]] = List(list1, list2)

    When("using the parse method with the listOfListOfString")
    val listOfReportDataV1: List[ReportDataV1] = ReportDataV1Parser.parse(lines = listOfListOfString)

    Then("the created list should equal the correct one")
    listOfReportDataV1 shouldEqual correctListOfReportDataV1
  }

  it should "create a map from a ReportDataV1 objects" in {
    val correctMapValues: Map[String, String] = Map(
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

    Given("a ReportDataV1")
    println(reportDataV1_1)

    When("using the parse method")
    val mapValues: Map[String, String] = ReportDataV1Processor.toMapValue(reportDataV1_1)

    Then("the created list should equal the correct one")
    mapValues shouldEqual correctMapValues
  }
}

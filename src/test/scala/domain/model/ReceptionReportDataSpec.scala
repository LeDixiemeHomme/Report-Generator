package fr.valle.report_generator
package domain.model

import customexceptions.IncompleteObjectInstantiationException
import domain.model.ReceptionReportData.ReceptionReportDataProcessor

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.{BeforeAndAfterEach, GivenWhenThen, PrivateMethodTester}

class ReceptionReportDataSpec extends AnyFlatSpec with PrivateMethodTester with GivenWhenThen with BeforeAndAfterEach with Matchers {

  var receptionReportData_1: ReceptionReportData = _
  var receptionReportData_2: ReceptionReportData = _

  override def beforeEach(): Unit = {
    receptionReportData_1 = TestDataProvider.provideReceptionReportData_1
  }

  it should "have a correct string representation" in {
    val correctStringValue: String = "ReceptionReportData{Nombre sorbonnes: 2, Mois: Janvier, Année: 2023, Nom établissement: Université Sorbonne Paris Nord, Ville: Villetaneuse, Département: Seine-Saint-Denis, Adresse: 1 Rue de la Croix Faron, Code postal: 93430, Jour: 15, Intervenant: John Doe, Sexe: M, Société soutraite: FabCorp, Nom1: Robert, Nom2: Dupont, Numéro d'affaire: ABC123}"

    Given("a ReceptionReportData")
    println(receptionReportData_1)

    When("using the toString method")
    val stringValue = receptionReportData_1.toString

    Then("stringValue shouldEqual correctStringValue")
    stringValue shouldEqual correctStringValue
  }

  it should "return correct values for its properties" in {

    Given("a ReceptionReportData")
    println(receptionReportData_1)

    Then("all properties have the right value")
    receptionReportData_1.nombreSorbonnes shouldEqual TestDataProvider.nombreSorbonnes1
    receptionReportData_1.mois shouldEqual TestDataProvider.mois1
    receptionReportData_1.annee shouldEqual TestDataProvider.annee1
    receptionReportData_1.nomEtablissement shouldEqual TestDataProvider.nomEtablissement1
    receptionReportData_1.ville shouldEqual TestDataProvider.ville1
    receptionReportData_1.departement shouldEqual TestDataProvider.departement1
    receptionReportData_1.adresse shouldEqual TestDataProvider.adresse1
    receptionReportData_1.codePostal shouldEqual TestDataProvider.codePostal1
    receptionReportData_1.jour shouldEqual TestDataProvider.jour1
    receptionReportData_1.intervenant shouldEqual TestDataProvider.intervenant1
    receptionReportData_1.sexe shouldEqual TestDataProvider.sexe1
    receptionReportData_1.societeSoutraite shouldEqual TestDataProvider.societeSoutraite1
    receptionReportData_1.nom1 shouldEqual TestDataProvider.nom11
    receptionReportData_1.nom2 shouldEqual TestDataProvider.nom21
    receptionReportData_1.numeroAffaire shouldEqual TestDataProvider.numeroAffaire1
  }

  it should "create a map from a ReceptionReportData objects" in {
    val correctMapValues: Map[String, String] = TestDataProvider.provideReceptionReportData_1MapValues

    Given("a ReceptionReportData")
    println(receptionReportData_1)

    When("using the parse method")
    val mapValues: Map[String, String] = ReceptionReportDataProcessor.toMapValue(receptionReportData_1)

    Then("the created list should equal the correct one")
    mapValues shouldEqual correctMapValues
  }

  it should "create a map from a ReceptionReportData objects with null values and throw a IncompleteObjectInstantiationException" in {
    val receptionReportDataNullValues: ReceptionReportData = TestDataProvider.provideReceptionReportNullValues_1
    val expectedExceptionMessage: String = "Un objet " + receptionReportDataNullValues.getClass +
        " possède une valeur null ce qui ne permet pas de construire le dictionnaire."

    Given("a ReceptionReportData with null values")
    println(receptionReportDataNullValues)

    When("using the parse method")
    val caughtException = intercept[IncompleteObjectInstantiationException] {
      ReceptionReportDataProcessor.toMapValue(receptionReportDataNullValues)
    }

    Then("the created list should equal the correct one")
    caughtException.getMessage shouldEqual expectedExceptionMessage
  }
}

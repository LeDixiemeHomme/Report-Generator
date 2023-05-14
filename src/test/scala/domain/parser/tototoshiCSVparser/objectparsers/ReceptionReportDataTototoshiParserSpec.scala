package fr.valle.report_generator
package domain.parser.tototoshiCSVparser.objectparsers

import customexceptions.{MissingCSVColumnException, NoRowInCSVException}
import domain.model.ReceptionReportData

import com.github.tototoshi.csv.{CSVReader, DefaultCSVFormat}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{BeforeAndAfterEach, GivenWhenThen, PrivateMethodTester}

import java.io.File

class ReceptionReportDataTototoshiParserSpec extends AnyFlatSpec with PrivateMethodTester with BeforeAndAfterEach with GivenWhenThen with Matchers with TableDrivenPropertyChecks {

  var receptionReportData_1: ReceptionReportData = _
  var receptionReportData_2: ReceptionReportData = _
  var receptionReportDataTototoshiParser: ReceptionReportDataTototoshiParser = _
  var filePath: String = _

  override def beforeEach(): Unit = {
    receptionReportData_1 = TestDataProvider.provideReceptionReportData_1
    receptionReportData_2 = TestDataProvider.provideReceptionReportData_2
    receptionReportDataTototoshiParser = ReceptionReportDataTototoshiParser()
  }

  it should "take a tototoshi.csv.CSVReader with a filePath to a well formatted file and parse it into a list of ReceptionReportData objects" in {

    val correctListOfReceptionReportData: List[ReceptionReportData] = List(receptionReportData_1, receptionReportData_2)
    val testData = Table("filePath",
      getClass.getResource("/reception-report-data-test.csv").getPath,
      getClass.getResource("/reception-report-data-test-random-colomn-order.csv").getPath
    )

    forAll(testData) {
      (filePath: String) => {
        Given("a file path to a well formatted csv file containing the properties of objects receptionReportData_1 and receptionReportData_2")
        println("filePath = " + filePath)
        implicit object MyFormat extends DefaultCSVFormat {
          override val delimiter = ';'
        }

        When("using the parse method with the tototoshi.csv.CSVReader with the filePath as argument")
        val listOfReceptionReportData: List[ReceptionReportData] = receptionReportDataTototoshiParser.parse(reader = CSVReader.open(new File(filePath)))

        Then("the created list should equal the correct one")
        listOfReceptionReportData shouldEqual correctListOfReceptionReportData

      }
    }
  }

  it should "take a tototoshi.csv.CSVReader with a wrongFilePath to a none well formatted file and throws the correct custom Exception" in {

    val testData = Table(("wrongFilePath", "caughtExceptionMessage", "exceptionType", "exceptionCauseType"),
      (
        getClass.getResource("/reception-report-data-test-missing-column.csv").getPath,
        "La colonne Departement est manquante dans le fichier de données CSV.",
        classOf[MissingCSVColumnException],
        new NoSuchElementException("key not found: Departement").toString),
      (
        getClass.getResource("/reception-report-data-test-without-row-data.csv").getPath,
        "Le fichier de données CSV ne contient pas de données.",
        classOf[NoRowInCSVException],
        "")
    )

    forAll(testData) {
      (wrongFilePath: String, caughtExceptionMessage: String, exceptionType: Class[_ <: Exception], exceptionCauseType: String) => {
        Given("a file path to a not well formatted csv file containing the properties of objects receptionReportData_1 and receptionReportData_2")
        println("wrongFilePath = " + wrongFilePath)
        implicit object MyFormat extends DefaultCSVFormat {
          override val delimiter = ';'
        }
        When("using the parse method with the tototoshi.csv.CSVReader with the wrongFilePath as argument")
        val caughtException = intercept[Exception] {
          receptionReportDataTototoshiParser.parse(reader = CSVReader.open(new File(wrongFilePath)))
        }

        Then("the caught exception should be correct")
        caughtException.getClass shouldEqual exceptionType
        caughtException.getMessage shouldEqual caughtExceptionMessage
        if (Option(caughtException.getCause).isDefined) caughtException.getCause.toString shouldEqual exceptionCauseType
      }
    }
  }
}

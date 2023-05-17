package fr.valle.report_generator
package domain.parser.tototoshiCSVparser

import customexceptions.DataFileNotFoundException
import domain.parser.tototoshiCSVparser.objectparsers.ReceptionReportDataTototoshiParser

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.{BeforeAndAfterEach, GivenWhenThen, PrivateMethodTester}

class TototoshiCsvParserSpec extends AnyFlatSpec with PrivateMethodTester with BeforeAndAfterEach with GivenWhenThen with Matchers {

  private var filePath: String = _
  private var csvParser: TototoshiCsvFileParser = _

  override def beforeEach(): Unit = {
    csvParser = TototoshiCsvFileParser()
  }

  it should "parse a list of receptionReportData when using ReceptionReportDataTototoshiParser Trait" in {
    val listExpected = List(TestDataProvider.provideReceptionReportData_1, TestDataProvider.provideReceptionReportData_2)

    Given("a file path")
    filePath = getClass.getResource("/reception-report-data-test.csv").getPath

    When("using the parseFile method with the ReceptionReportDataTototoshiParser Trait")
    val parsedReceptionReportDataList = csvParser.parseFile(csvFilePath = filePath)(ReceptionReportDataTototoshiParser())

    Then("the list created from the parsing is equal to the listExpected")
    parsedReceptionReportDataList shouldEqual listExpected
  }

  it should " throws an DataFileNotFoundException when the file does not exist" in {
    val listExpected = List(TestDataProvider.provideReceptionReportData_1, TestDataProvider.provideReceptionReportData_2)

    Given("a file path to a file that does not exist")
    filePath = getClass.getResource("/reception-report-data-test.csv").getPath

    When("using the parseFile method with the ReceptionReportDataTototoshiParser Trait")
    val caughtException = intercept[DataFileNotFoundException] {
      csvParser.parseFile(csvFilePath = filePath + "/does-not-exist")(ReceptionReportDataTototoshiParser())
    }

    Then("the caught exception should be correct")
    caughtException.getMessage shouldEqual "Le fichier de données \"" + getClass.getResource("/reception-report-data-test.csv").getPath + "/does-not-exist\" est introuvable."
  }
}

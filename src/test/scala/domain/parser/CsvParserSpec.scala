package fr.valle.report_generator
package domain.parser

import domain.model.ReceptionReportData.ReceptionReportDataParser

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.{BeforeAndAfterEach, GivenWhenThen, PrivateMethodTester}

class CsvParserSpec extends AnyFlatSpec with PrivateMethodTester with BeforeAndAfterEach with GivenWhenThen with Matchers {

  private var filePath: String = _
  private var csvParser: CsvParser = _

  override def beforeEach(): Unit = {
    csvParser = CsvParser()
  }

  it should "parse a list of receptionReportData when using ReceptionReportDataParser Trait" in {
    val listExpected = List(TestDataProvider.provideReceptionReportData_1, TestDataProvider.provideReceptionReportData_2)

    Given("a file path")
    filePath = getClass.getResource("/reception-report-data-test.csv").getPath

    When("using the parseFile method with the ReceptionReportDataParser Trait")
    val parsedReceptionReportDataList = csvParser.parseFile(filePath = filePath)(ReceptionReportDataParser)

    Then("the list created from the parsing is equal to the listExpected")
    parsedReceptionReportDataList shouldEqual listExpected
  }
}

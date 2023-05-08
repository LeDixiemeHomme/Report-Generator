package fr.valle.report_generator
package services.parsing

import domain.model.ReceptionReportData
import domain.model.ReceptionReportData.ReceptionReportDataParser

import org.scalatest.featurespec.AnyFeatureSpecLike
import org.scalatest.matchers.should.Matchers
import org.scalatest.{BeforeAndAfterEach, GivenWhenThen}

class ParsingCsvServiceFeatureSpec extends AnyFeatureSpecLike with GivenWhenThen with BeforeAndAfterEach with Matchers {

  private var parsingReceptionReportDataCsvService: ParsingServiceTrait[ReceptionReportData] = _
  private var filePath: String = _

  override def beforeEach(): Unit = {
    parsingReceptionReportDataCsvService = ParsingCsvService()
  }

  Feature("Using ParsingServiceTrait, with the ReceptionReportData type, parse method with the ReceptionReportDataParser") {

    Scenario("Using a filePath to create a parsingReceptionReportDataResult containing a list of ReceptionReportData objects") {

      val expectedParsingReceptionReportDataResult: ParsingResult[ReceptionReportData] =
        new ParsingResult(parsedData = List(
          TestDataProvider.provideReceptionReportData_1,
          TestDataProvider.provideReceptionReportData_2)
        )

      Given("a filePath")
      filePath = getClass.getResource("/reception-report-data-test.csv").getPath

      When("using the ParsingServiceTrait.process method with the ReceptionReportDataParser")
      val parsingReceptionReportDataResult: ParsingResult[ReceptionReportData] = parsingReceptionReportDataCsvService.parse(filePath = filePath)(ReceptionReportDataParser)

      Then("the result should be correct")
      parsingReceptionReportDataResult.toString shouldEqual expectedParsingReceptionReportDataResult.toString
    }
  }
}

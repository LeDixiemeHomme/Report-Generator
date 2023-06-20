package fr.valle.report_generator
package services.parsing

import app.path.FilePath
import domain.csv.parser.tototoshiCSVparser.TototoshiCsvFileParser
import domain.csv.parser.tototoshiCSVparser.objectparsers.ReceptionReportDataTototoshiParser
import domain.model.ReceptionReportData
import features.results.ParsingResult
import features.services.parsing.{ParsingCsvService, ParsingServiceTrait}

import org.scalatest.featurespec.AnyFeatureSpecLike
import org.scalatest.matchers.should.Matchers
import org.scalatest.{BeforeAndAfterEach, GivenWhenThen}

class ParsingCsvServiceFeatureSpec extends AnyFeatureSpecLike with GivenWhenThen with BeforeAndAfterEach with Matchers {

  private var parsingReceptionReportDataCsvService: ParsingServiceTrait[ReceptionReportData] = _
  private var filePath: String = _

  override def beforeEach(): Unit = {
    parsingReceptionReportDataCsvService = ParsingCsvService(TototoshiCsvFileParser())
  }

  Feature("Using ParsingServiceTrait, with the ReceptionReportData type, parse method with the ReceptionReportDataTototoshiParser") {

    Scenario("Using a filePath to create a parsingReceptionReportDataResult containing a list of ReceptionReportData objects") {

      val expectedParsingReceptionReportDataResult: ParsingResult[ReceptionReportData] =
        ParsingResult(isSuccess = true, popUpMessage = "Successfully parsed", parsedData = List(
          TestDataProvider.provideReceptionReportData_1,
          TestDataProvider.provideReceptionReportData_2)
        )

      Given("a filePath")
      filePath = getClass.getResource("/reception-report-data-test.csv").getPath

      When("using the ParsingServiceTrait.process method with the ReceptionReportDataTototoshiParser")
      val parsingReceptionReportDataResult: ParsingResult[ReceptionReportData] = parsingReceptionReportDataCsvService
        .parse(filePath = FilePath.stringToFilePath(filePath))(ReceptionReportDataTototoshiParser())

      Then("the result should be correct")
      parsingReceptionReportDataResult.toString shouldEqual expectedParsingReceptionReportDataResult.toString
    }
  }
}

package fr.valle.report_generator
package services.processing

import domain.model.ReceptionReportData
import domain.model.ReceptionReportData.ReceptionReportDataProcessor

import org.scalatest.featurespec.AnyFeatureSpecLike
import org.scalatest.matchers.should.Matchers
import org.scalatest.{BeforeAndAfterEach, GivenWhenThen}

class ProcessingDataServiceFeatureSpec extends AnyFeatureSpecLike with GivenWhenThen with BeforeAndAfterEach with Matchers {

  private var processingReceptionReportDataService: ProcessingServiceTrait[ReceptionReportData] = _

  override def beforeEach(): Unit = {
    processingReceptionReportDataService = ProcessingDataService()
  }

  Feature("Using ProcessingServiceTrait, with the ReceptionReportData type, process method with the ReceptionReportDataProcessor") {

    Scenario("Using a ReceptionReportData object to create a processingResult containing an mapValues") {

      val expectedProcessingResult: ProcessingResult = new ProcessingResult(TestDataProvider.provideReceptionReportData_1MapValues)

      Given("a ReceptionReportData object")
      val receptionReportData: ReceptionReportData = TestDataProvider.provideReceptionReportData_1

      When("using the ProcessingServiceTrait.process method with the ReceptionReportDataProcessor")
      val processingResult: ProcessingResult = processingReceptionReportDataService.process(
        dataToProcess = receptionReportData)(ReceptionReportDataProcessor)

      Then("the result should be correct")
      processingResult.toString shouldEqual expectedProcessingResult.toString
    }
  }
}

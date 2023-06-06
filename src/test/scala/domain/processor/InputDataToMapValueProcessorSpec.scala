package fr.valle.report_generator
package domain.processor

import domain.model.ReceptionReportData
import domain.model.ReceptionReportData.ReceptionReportDataProcessor

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.{BeforeAndAfterEach, GivenWhenThen, PrivateMethodTester}

class InputDataToMapValueProcessorSpec extends AnyFlatSpec with PrivateMethodTester with BeforeAndAfterEach with GivenWhenThen with Matchers {

  private var inputDataToMapValueProcessor: InputDataToMapValueProcessor = _

  override def beforeEach(): Unit = {
    inputDataToMapValueProcessor = InputDataToMapValueProcessor()
  }

  it should "process a receptionReportData into a mapValues when using ReceptionReportDataProcessor Trait" in {
    val mapValuesExpected: Map[String, String] = TestDataProvider.provideReceptionReportData_1MapValues

    Given("a ReceptionReportData")
    val receptionReportData_1: ReceptionReportData = TestDataProvider.provideReceptionReportData_1

    When("using the processToMapValue method with the ReceptionReportDataProcessor Trait")
    val mapValues: Map[String, String] = inputDataToMapValueProcessor.processToMapValue(receptionReportData_1)(ReceptionReportDataProcessor)

    Then("the mapValues created from the processing is equal to the mapValuesExpected")
    mapValues shouldEqual mapValuesExpected
  }
}

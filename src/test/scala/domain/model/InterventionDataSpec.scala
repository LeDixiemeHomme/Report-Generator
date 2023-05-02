package fr.valle.report_generator
package domain.model

import org.scalatest.{BeforeAndAfterEach, GivenWhenThen, PrivateMethodTester}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class InterventionDataSpec extends AnyFlatSpec with PrivateMethodTester with GivenWhenThen with BeforeAndAfterEach with Matchers {
  it should "have a correct string representation" in {
    val correctStringValue: String = "InterventionData: { experiment1; Date: 2022-01-01; Sample Name: sample1; Mass (g): 10.0; Volume (mL): 5.0; Temperature (°C): 25.0 }"

    Given("an InterventionData")
    val data = InterventionData("experiment1", "2022-01-01", "sample1", 10.0, 5.0, 25.0)

    When("using the toString method")
    val stringValue = data.toString

    Then("stringValue shouldEqual correctStringValue")
    stringValue shouldEqual correctStringValue
  }

  it should "calculate mass times volume correctly" in {
    val correctResult: Double = 50.0

    Given("an InterventionData")
    val data = InterventionData("experiment1", "2022-01-01", "sample1", 10.0, 5.0, 25.0)

    When("using the private massTimesVolume method")
    val result = data invokePrivate PrivateMethod[Double](Symbol("massTimesVolume"))()

    Then("result shouldEqual correctResult")
    result shouldEqual correctResult
  }

  it should "return correct values for its properties" in {

    Given("an InterventionData")

    val interventionData = InterventionData("experiment1", "2022-01-01", "sample1", 10.0, 5.0, 25.0)
    interventionData.experimentName shouldEqual "experiment1"
    interventionData.date shouldEqual "2022-01-01"
    interventionData.sampleName shouldEqual "sample1"
    interventionData.mass shouldEqual 10.0
    interventionData.volume shouldEqual 5.0
    interventionData.temperature shouldEqual 25.0
  }
}

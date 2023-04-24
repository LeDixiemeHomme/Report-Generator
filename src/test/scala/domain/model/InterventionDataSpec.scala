package fr.valle.report_generator
package domain.model

import org.scalatest.PrivateMethodTester
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class InterventionDataSpec extends AnyFlatSpec with PrivateMethodTester {
  "An InterventionData" should "have a correct string representation" in {
    val data = InterventionData("experiment1", "2022-01-01", "sample1", 10.0, 5.0, 25.0)
    data.toString shouldEqual "InterventionData: { experiment1; Date: 2022-01-01; Sample Name: sample1; Mass (g): 10.0; Volume (mL): 5.0; Temperature (°C): 25.0 }"
  }

  it should "calculate mass times volume correctly" in {
    val data = InterventionData("experiment1", "2022-01-01", "sample1", 10.0, 5.0, 25.0)
    val result = data invokePrivate PrivateMethod[Double](Symbol("massTimesVolume"))()
    assert(result == 51.0)
  }

  it should "return correct values for its properties" in {
    val data = InterventionData("experiment1", "2022-01-01", "sample1", 10.0, 5.0, 25.0)
    data.experimentName shouldEqual "experiment1"
    data.date shouldEqual "2022-01-01"
    data.sampleName shouldEqual "sample1"
    data.mass shouldEqual 10.0
    data.volume shouldEqual 5.0
    data.temperature shouldEqual 25.0
  }
}

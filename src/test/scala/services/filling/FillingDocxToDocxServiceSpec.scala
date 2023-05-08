package fr.valle.report_generator
package services.filling

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.{BeforeAndAfterEach, GivenWhenThen, PrivateMethodTester}

class FillingDocxToDocxServiceSpec extends AnyFlatSpec with PrivateMethodTester with BeforeAndAfterEach with GivenWhenThen with Matchers {
  var myFillingDocxToDocxService: FillingDocxToDocxService = _

  override def beforeEach(): Unit = {
    myFillingDocxToDocxService = FillingDocxToDocxService()
  }

  it should "have a correct default value file name" in {
    Given("the private method defaultValue")
    val defaultValue = PrivateMethod[String](Symbol("defaultValue"))

    When("using the defaultValue private method")
    val result1 = myFillingDocxToDocxService invokePrivate defaultValue()

    Then("the result should be correct")
    result1 shouldEqual "default-name-value"
  }
}

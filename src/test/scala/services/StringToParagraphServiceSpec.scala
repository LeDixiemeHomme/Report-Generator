package fr.valle.report_generator
package services

import features.services.StringToParagraphService

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.{BeforeAndAfterEach, GivenWhenThen, PrivateMethodTester}

class StringToParagraphServiceSpec extends AnyFlatSpec with PrivateMethodTester with GivenWhenThen with BeforeAndAfterEach with Matchers {

  private var stringToParagraphService: StringToParagraphService = _

  override def beforeEach(): Unit = {
    stringToParagraphService = StringToParagraphService()
  }

  "createPopupMessageTextSeq" should "return an empty sequence when popupMessage is empty" in {
    val result = stringToParagraphService.action("", 10)
    result shouldEqual List.empty
  }

  it should "return a sequence containing one Text when popupMessage is shorter than the limiter" in {
    val result = stringToParagraphService.action("Hello world", 50)
    result.size shouldEqual 1
    result.head shouldEqual "Hello world"
  }

  it should "return a sequence containing multiple Text elements when popupMessage is longer than the limiter" in {
    val popupMessage = "This is a long message that needs to be split into multiple Text elements"
    val result = stringToParagraphService.action(popupMessage, 20)
    result.size shouldEqual 5
    result.mkString(" ") shouldEqual popupMessage
  }

  it should "return a sequence containing multiple Text elements when popupMessage is longer than the limiter 2" in {
    val popupMessage = "This is a long message that needs to be split into multiple Text elements, Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"
    val result = stringToParagraphService.action(popupMessage, 50)
    result.size shouldEqual 4
    result.mkString(" ") shouldEqual popupMessage
  }
}

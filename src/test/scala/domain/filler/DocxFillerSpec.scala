package fr.valle.report_generator
package domain.filler

import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.scalatest.{BeforeAndAfterEach, PrivateMethodTester}
import org.scalatest.flatspec.AnyFlatSpec

class DocxFillerSpec extends AnyFlatSpec with PrivateMethodTester with BeforeAndAfterEach {
  private var document: XWPFDocument = _
  private var docxFiller: DocxFiller = _
  private val valuesMap: Map[String, String] = Map("#ReplaceMe#" -> "Replaced")

  override def beforeEach(): Unit = {
    docxFiller = new DocxFiller
    document = new XWPFDocument()
  }

  "An XWPFDocument" should "have its tag replaced by the value in the map" in {
    // GIVEN // Create a paragraph with the key to replace
    val paragraph1 = document.createParagraph()
    val run1 = paragraph1.createRun()
    run1.setText("A text #ReplaceMe# to test.")

    // WHEN // Call the function to replace values in the document
    docxFiller.fillDocx(document, valuesMap)

    // THEN // Check that the value was replaced
    assert(document.getParagraphs.get(0).getText == "A text Replaced to test.")
  }

  "An XWPFDocument" should "not have its tag replaced by the value in the map" in {
    // GIVEN // Create another paragraph with a different text
    val paragraph2 = document.createParagraph()
    val run2 = paragraph2.createRun()
    run2.setText("A text #DoNotReplaceMe# to test.")

    // WHEN // Call the function to replace values in the document
    docxFiller.fillDocx(document, valuesMap)

    // THEN // Check that the other value was not replaced
    assert(document.getParagraphs.get(0).getText == "A text #DoNotReplaceMe# to test.")
  }
}

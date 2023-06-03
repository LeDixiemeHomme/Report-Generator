package fr.valle.report_generator
package domain.writer

import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.{BeforeAndAfterEach, GivenWhenThen, PrivateMethodTester}

class DocxWriterSpec extends AnyFlatSpec with PrivateMethodTester with BeforeAndAfterEach with GivenWhenThen with Matchers {

  private val sourcePath: String = getClass.getResource("/").getPath

  private var docxWriter: DocxWriter = _
  private var document: XWPFDocument = _
  private var outputDirPath: String = _
  private var fileName: String = _

  override def beforeEach(): Unit = {
    docxWriter = DocxWriter()
    document = new XWPFDocument()
  }

  it should "write a XWPFDocument at the right place with the right file name" in {
    Given("an outputDirPath and a fileName")
    outputDirPath = sourcePath
    fileName = "file-result"

    When("using the write method")
    val result: WriteResult = docxWriter.write(templateDoc = document, outputDirPath = outputDirPath, fileName = fileName)

    Then("the result should be correct")
    result.outputMessage shouldEqual "Successfully written in " + outputDirPath
  }

  it should "have a built final path" in {
    Given("the protected method constructFinalPath")
    val constructFinalPath = PrivateMethod[String](Symbol("constructFinalPath"))

    When("using the constructFinalPath protected method with a set of arguments")
    val result1 = docxWriter invokePrivate constructFinalPath("outputDirPath", "fileName", ".txt")
    val result2 = docxWriter invokePrivate constructFinalPath("outputDirPath/", "/fileName", ".txt")
    val result3 = docxWriter invokePrivate constructFinalPath("outputDirPath/", "fileName", ".txt")
    val result4 = docxWriter invokePrivate constructFinalPath("outputDirPath", "/fileName.txt", ".txt")

    val result5 = docxWriter invokePrivate constructFinalPath("outputDirPath\\", "\\fileName", ".txt")
    val result6 = docxWriter invokePrivate constructFinalPath("outputDirPath\\", "fileName.txt", ".txt")
    val result7 = docxWriter invokePrivate constructFinalPath("outputDirPath", "\\fileName.txt", ".txt")

    val result8 = docxWriter invokePrivate constructFinalPath("outputDirPath", "fileName.txt", ".txt")
    val result9 = docxWriter invokePrivate constructFinalPath("outputDirPath", "fileName.pdf", ".txt")

    Then("all results should be correct")
    result1 shouldEqual "outputDirPath/fileName.txt"
    result2 shouldEqual "outputDirPath/fileName.txt"
    result3 shouldEqual "outputDirPath/fileName.txt"
    result4 shouldEqual "outputDirPath/fileName.txt"
    result5 shouldEqual "outputDirPath\\fileName.txt"
    result6 shouldEqual "outputDirPath\\fileName.txt"
    result7 shouldEqual "outputDirPath\\fileName.txt"
    result8 shouldEqual "outputDirPath/fileName.txt"
    result9 shouldEqual "outputDirPath/fileName.pdf.txt"
  }
}

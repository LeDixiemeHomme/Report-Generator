package fr.valle.report_generator
package domain.path

import app.LocalOS
import app.LocalOS.OSs

import org.scalatest.Assertion
import org.scalatest.matchers.should.Matchers

object TestFilePathProvider extends Matchers {
  def assertByOs(expectedWindows: Object, actualWindows: Object, expectedOthers: Object, actualOthers: Object): Assertion = {
    LocalOS.os match {
      case OSs.WINDOWS => actualWindows shouldEqual expectedWindows
      case OSs.MACOS | OSs.LINUX | OSs.OTHER => expectedOthers shouldEqual actualOthers
    }
  }
}
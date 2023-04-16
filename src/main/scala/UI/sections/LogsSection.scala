package fr.valle.report_generator
package UI.sections

import logging.LogsKeeper

import org.apache.logging.log4j.scala.Logging
import scalafx.scene.layout.HBox

class LogsSection extends Logging with IsASectionTrait {
  private val section: HBox = new HBox{
    children = LogsKeeper.buffer
  }
  override def mySection: HBox = section
}

object LogsSection {
  def apply(): LogsSection = new LogsSection()
}

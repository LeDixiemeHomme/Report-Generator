package fr.valle.report_generator
package UI.sections.logssection

import UI.sections.IsASectionTrait
import logging.LogsKeeper
import logging.loggingobserverpattern.LogsSectionObserver

import scalafx.scene.control.ScrollPane
import scalafx.scene.layout.VBox

trait IsALogsSectionTrait extends IsASectionTrait with LogsSectionObserver {

  LogsKeeper.addObserver(this)

  def myVBox: VBox

  def myScrollPane: ScrollPane
}

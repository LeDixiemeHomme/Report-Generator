package fr.valle.report_generator
package UI.sections.logssection

import UI.sections.IsASectionTrait
import logging.LogsKeeper
import logging.loggingobserverpattern.LogsSectionObserverTrait

import scalafx.scene.control.ScrollPane
import scalafx.scene.layout.VBox

trait IsALogsSectionTrait extends IsASectionTrait with LogsSectionObserverTrait {

  LogsKeeper.addObserver(this)

  def myVBox: VBox

  def myScrollPane: ScrollPane
}

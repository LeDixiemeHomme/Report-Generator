package fr.valle.report_generator
package logging.loggingobserverpattern

import logging.Log

trait LoggingSubjectTrait {
  private var observers: List[LogsSectionObserverTrait] = List()

  def addObserver(observer: LogsSectionObserverTrait): Unit = {
    observers = observer :: observers
  }

  def removeObserver(observer: LogsSectionObserverTrait): Unit = {
    observers = observers.filter(_ != observer)
  }

  def notifyLoggingObservers(log: Log): Unit = {
    observers.foreach(_.update(log = log))
  }
}

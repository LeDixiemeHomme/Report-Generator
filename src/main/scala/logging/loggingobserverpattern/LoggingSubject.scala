package fr.valle.report_generator
package logging.loggingobserverpattern

trait LoggingSubject {
  private var observers: List[LogsSectionObserver] = List()

  def addObserver(observer: LogsSectionObserver): Unit = {
    observers = observer :: observers
  }

  def removeObserver(observer: LogsSectionObserver): Unit = {
    observers = observers.filter(_ != observer)
  }

  def notifyLoggingObservers(log: String): Unit = {
    observers.foreach(_.update(log = log))
  }
}

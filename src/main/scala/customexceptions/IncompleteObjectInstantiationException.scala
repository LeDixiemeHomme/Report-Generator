package fr.valle.report_generator
package customexceptions

case class IncompleteObjectInstantiationException(wronglyInstantiateObject: Object, cause: Option[Throwable] = None)
  extends Exception(String.format("Un objet " + wronglyInstantiateObject.getClass.toString +
    " possède une valeur null ce qui ne permet pas de construire le dictionnaire.")) {
  initCause(cause.orNull)
}
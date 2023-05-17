package fr.valle.report_generator
package customexceptions


class IncompleteObjectInstantiationException(val wronglyInstantiateObject: Object, val cause: Option[Throwable] = None)
  extends Exception(String.format("Un objet " + wronglyInstantiateObject.getClass.toString +
    " possède une valeur null ce qui ne permet pas de construire le dictionnaire.")) {
  initCause(cause.orNull)
}
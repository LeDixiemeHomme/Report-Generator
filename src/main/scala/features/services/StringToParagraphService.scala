package fr.valle.report_generator
package features.services

import org.apache.logging.log4j.scala.Logging

import scala.collection.mutable.ListBuffer

class StringToParagraphService extends Logging {


  def action(popupMessage: String, limiter: Int): List[String] = {

    if (popupMessage.isEmpty) return List.empty

    val childBuffer: ListBuffer[String] = ListBuffer()

    val popupMessageWords: List[String] = popupMessage.split(" ").toList

    val stringBuffer = new StringBuffer()

    stringBuffer.append(popupMessageWords.head)

    popupMessageWords.tail.foreach(word => {
      if (stringBuffer.length() < limiter && (stringBuffer.length() + word.length) < limiter) {
        stringBuffer.append(" " + word)
      } else {
        childBuffer.addOne(stringBuffer.toString)
        stringBuffer.delete(0, stringBuffer.length)
        stringBuffer.append(word)
      }
    })

    childBuffer.addOne(stringBuffer.toString)
    childBuffer.toList
  }
}

object StringToParagraphService {
  def apply(): StringToParagraphService = new StringToParagraphService()
}
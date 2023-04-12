package fr.valle.report_generator
package services.processing

class ProcessingCarDataService() extends ProcessingServiceTrait {
  val valuesMap: Map[String, String] = Map(
    "<Name>" -> "John Doe",
    "<Age>" -> "30",
    "<City>" -> "New York"
  )
  override def process(data: List[Any]): ProcessingResult = ProcessingResult(valuesMap)
}

object ProcessingCarDataService {
  def apply(): ProcessingCarDataService = new ProcessingCarDataService()
}
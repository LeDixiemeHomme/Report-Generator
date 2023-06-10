package fr.valle.report_generator
package domain.parser.tototoshiCSVparser.objectparsers

import customexceptions.{MissingCSVColumnException, NoRowInCSVException}
import domain.model.ReceptionReportData
import domain.parser.IsAnObjectParserTrait
import logging.{Levels, Log, LogsKeeper}

import com.github.tototoshi.csv.CSVReader
import org.apache.logging.log4j.scala.Logging

import scala.util.{Failure, Success, Try}

class ReceptionReportDataTototoshiParser extends IsAnObjectParserTrait[ReceptionReportData] with Logging {

  def parse(reader: Object): List[ReceptionReportData] = {
    LogsKeeper.keepAndLog(extLogger = logger, log = Log(message = "Using ReceptionReportDataTototoshiParser.parse()", level = Levels.INFO), classFrom = getClass)

    val tototoshiCSVReader: CSVReader = reader.asInstanceOf[CSVReader]

    val receptionReportDataIterator: List[ReceptionReportData] = tryReadSafely(reader = tototoshiCSVReader) match {
      case Success(list: List[ReceptionReportData]) if list.nonEmpty => list
      case Success(_: List[ReceptionReportData]) => throw NoRowInCSVException()

      case Failure(noSuchElementException: NoSuchElementException) => throw MissingCSVColumnException(noSuchElementExceptionMessage = noSuchElementException.getMessage, cause = Some(noSuchElementException))
      case Failure(exception) => throw exception
    }

    receptionReportDataIterator
  }

  private def tryReadSafely(reader: CSVReader): Try[List[ReceptionReportData]] = {
    Try {
      val receptionReportDataIterator: Iterator[ReceptionReportData] = reader.iteratorWithHeaders.map(
        row => {
          val nombreSorbonnes = row("Nombre_sorbonnes")
          val mois = row("Mois")
          val annee = row("Annee")
          val nomEtablissement = row("Nom_etablissement")
          val ville = row("Ville")
          val departement = row("Departement")
          val adresse = row("Adresse")
          val codePostal = row("Code_postal")
          val jour = row("Jour")
          val intervenant = row("Intervenant")
          val sexe = row("Sexe")
          val societeSoutraite = row("Societe_sous_traite")
          val nom1 = row("Nom1")
          val nom2 = row("Nom2")
          val numeroAffaire = row("Numero_d_affaire")

          ReceptionReportData(nombreSorbonnes, mois, annee, nomEtablissement, ville, departement, adresse,
            codePostal, jour, intervenant, sexe, societeSoutraite, nom1, nom2, numeroAffaire)
        }
      )

      receptionReportDataIterator.toList
    }
  }
}

object ReceptionReportDataTototoshiParser {
  def apply(): ReceptionReportDataTototoshiParser = new ReceptionReportDataTototoshiParser()
}
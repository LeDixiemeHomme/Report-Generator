package fr.valle.report_generator
package domain.model

import customexceptions.IncompleteObjectInstantiationException
import domain.processor.InputDataToMapValueProcessor.ToMapValueProcessorTrait
import logging.LogsKeeper

import org.apache.logging.log4j.scala.Logging

import scala.util.{Failure, Success, Try}

case class ReceptionReportData(nombreSorbonnes: String, mois: String, annee: String, nomEtablissement: String,
                               ville: String, departement: String, adresse: String, codePostal: String,
                               jour: String, intervenant: String, sexe: String, societeSousTraite: String,
                               nom1: String, nom2: String, numeroAffaire: String) {
  override def toString: String = {
    s"ReceptionReportData{Nombre sorbonnes: $nombreSorbonnes, Mois: $mois, Année: $annee, Nom établissement: $nomEtablissement, " +
      s"Ville: $ville, Département: $departement, Adresse: $adresse, Code postal: $codePostal, Jour: $jour, " +
      s"Intervenant: $intervenant, Sexe: $sexe, Société sous-traite: $societeSousTraite, Nom1: $nom1, Nom2: $nom2, Numéro d'affaire: $numeroAffaire}"
  }

  def createFileName: String =
    s"AFC ${this.societeSousTraite} - Rapport de réception ${this.nomEtablissement} (${this.departement}) - ${this.mois} ${this.annee}"
}

object ReceptionReportData extends Logging {
  private def isNotPossessingNull(data: ReceptionReportData): Boolean = {
    Option(data.nombreSorbonnes).isDefined &&
      Option(data.mois).isDefined &&
      Option(data.annee).isDefined &&
      Option(data.nomEtablissement).isDefined &&
      Option(data.ville).isDefined &&
      Option(data.departement).isDefined &&
      Option(data.adresse).isDefined &&
      Option(data.codePostal).isDefined &&
      Option(data.jour).isDefined &&
      Option(data.intervenant).isDefined &&
      Option(data.sexe).isDefined &&
      Option(data.societeSousTraite).isDefined &&
      Option(data.nom1).isDefined &&
      Option(data.nom2).isDefined &&
      Option(data.numeroAffaire).isDefined
  }
  object ReceptionReportDataProcessor extends ToMapValueProcessorTrait[ReceptionReportData] {

    override def toMapValue(inputData: ReceptionReportData): Map[String, String] = {
      LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.INFO, "Using ReceptionReportDataProcessor.toMapValue()", classFrom = getClass)

      val mapValue: Map[String, String] = tryToMapValueSafely(inputData = inputData) match {
        case Success(mapValue: Map[String, String]) => mapValue

        case Failure(nullPointerException: NullPointerException) => throw IncompleteObjectInstantiationException(
          wronglyInstantiateObject = inputData, cause = Some(nullPointerException))
        case Failure(exception) => throw exception
      }

      mapValue
    }

    private def tryToMapValueSafely(inputData: ReceptionReportData): Try[Map[String, String]] = {
      Try {
        if (!isNotPossessingNull(data = inputData)) throw new NullPointerException()
        Map(
          "#nombre_sorbonne#" -> inputData.nombreSorbonnes,
          "#mois#" -> inputData.mois,
          "#annee#" -> inputData.annee,
          "#nom_etablissement#" -> inputData.nomEtablissement,
          "#ville#" -> inputData.ville,
          "#departement#" -> inputData.departement,
          "#adresse#" -> inputData.adresse,
          "#code_postal#" -> inputData.codePostal,
          "#jour#" -> inputData.jour,
          "#intervenant#" -> inputData.intervenant,
          "#sexe#" -> inputData.sexe,
          "#societe_soutraite#" -> inputData.societeSousTraite,
          "#nom1#" -> inputData.nom1,
          "#nom2#" -> inputData.nom2,
          "#numero_d_affaire#" -> inputData.numeroAffaire
        )
      }
    }
  }
}
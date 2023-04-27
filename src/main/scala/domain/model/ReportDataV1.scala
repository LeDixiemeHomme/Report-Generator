package fr.valle.report_generator
package domain.model

import domain.parser.CsvParser.FileParserTrait
import domain.processor.InputDataToMapValueProcessor.ToMapValueProcessorTrait
import logging.LogsKeeper

import org.apache.logging.log4j.scala.Logging

case class ReportDataV1(nombreSorbonnes: String, mois: String, annee: String, nomEtablissement: String,
                        ville: String, departement: String, adresse: String, codePostal: String,
                        jour: String, intervenant: String, sexe: String, societeSoutraite: String,
                        nom1: String, nom2: String, numeroAffaire: String) {
  override def toString: String = {
    s"ReportDataV1{Nombre sorbonnes: $nombreSorbonnes, Mois: $mois, Année: $annee, Nom établissement: $nomEtablissement, " +
      s"Ville: $ville, Département: $departement, Adresse: $adresse, Code postal: $codePostal, Jour: $jour, " +
      s"Intervenant: $intervenant, Sexe: $sexe, Société soutraite: $societeSoutraite, Nom1: $nom1, Nom2: $nom2, Numéro d'affaire: $numeroAffaire}"
  }
}

object ReportDataV1 extends Logging {
  object ReportDataV1Parser extends FileParserTrait[ReportDataV1] {
    def parse(lines: List[List[String]]): List[ReportDataV1] = {

      LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.INFO, "Using ReportDataV1Parser", classFrom = getClass)

      lines.map(row => {
        val nombreSorbonnes = row(0)
        val mois = row(1)
        val annee = row(2)
        val nomEtablissement = row(3)
        val ville = row(4)
        val departement = row(5)
        val adresse = row(5)
        val codePostal = row(5)
        val jour = row(5)
        val intervenant = row(5)
        val sexe = row(5)
        val societeSoutraite = row(5)
        val nom1 = row(5)
        val nom2 = row(5)
        val numeroAffaire = row(5)
        ReportDataV1(nombreSorbonnes, mois, annee, nomEtablissement, ville, departement, adresse, codePostal,
          jour, intervenant, sexe, societeSoutraite, nom1, nom2, numeroAffaire)
      })
    }
  }

  object InterventionDataProcessor extends ToMapValueProcessorTrait[ReportDataV1] {
    override def toMapValue(inputData: ReportDataV1): Map[String, String] = {

      LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.INFO, "ReportDataV1Parser.toMapValue()", classFrom = getClass)

      //todo to implement
      Map(
        "#Nombre_sorbonnes#" -> inputData.nombreSorbonnes,
        "#Mois#" -> inputData.mois,
        "#Année#" -> inputData.annee
      )
    }
  }

}
package fr.valle.report_generator
package domain.model

import domain.parser.CsvParser.FileParserTrait
import domain.processor.InputDataToMapValueProcessor.ToMapValueProcessorTrait
import logging.LogsKeeper

import org.apache.logging.log4j.scala.Logging

case class ReceptionReportData(nombreSorbonnes: String, mois: String, annee: String, nomEtablissement: String,
                               ville: String, departement: String, adresse: String, codePostal: String,
                               jour: String, intervenant: String, sexe: String, societeSoutraite: String,
                               nom1: String, nom2: String, numeroAffaire: String) {
  override def toString: String = {
    s"ReceptionReportData{Nombre sorbonnes: $nombreSorbonnes, Mois: $mois, Année: $annee, Nom établissement: $nomEtablissement, " +
      s"Ville: $ville, Département: $departement, Adresse: $adresse, Code postal: $codePostal, Jour: $jour, " +
      s"Intervenant: $intervenant, Sexe: $sexe, Société soutraite: $societeSoutraite, Nom1: $nom1, Nom2: $nom2, Numéro d'affaire: $numeroAffaire}"
  }
}

object ReceptionReportData extends Logging {
  object ReceptionReportDataParser extends FileParserTrait[ReceptionReportData] {
    def parse(lines: List[List[String]]): List[ReceptionReportData] = {

      LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.INFO, "Using ReceptionReportDataParser.parse()", classFrom = getClass)

      lines.map(row => {
        val nombreSorbonnes = row(0)
        val mois = row(1)
        val annee = row(2)
        val nomEtablissement = row(3)
        val ville = row(4)
        val departement = row(5)
        val adresse = row(6)
        val codePostal = row(7)
        val jour = row(8)
        val intervenant = row(9)
        val sexe = row(10)
        val societeSoutraite = row(11)
        val nom1 = row(12)
        val nom2 = row(13)
        val numeroAffaire = row(14)
        ReceptionReportData(nombreSorbonnes, mois, annee, nomEtablissement, ville, departement, adresse, codePostal,
          jour, intervenant, sexe, societeSoutraite, nom1, nom2, numeroAffaire)
      })
    }
  }

  object ReceptionReportDataProcessor extends ToMapValueProcessorTrait[ReceptionReportData] {
    override def toMapValue(inputData: ReceptionReportData): Map[String, String] = {
      LogsKeeper.keepAndLog(extLogger = logger, LogsKeeper.INFO, "Using ReceptionReportDataProcessor.toMapValue()", classFrom = getClass)

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
        "#societe_soutraite#" -> inputData.societeSoutraite,
        "#nom1#" -> inputData.nom1,
        "#nom2#" -> inputData.nom2,
        "#numero_d_affaire#" -> inputData.numeroAffaire
      )
    }
  }

}
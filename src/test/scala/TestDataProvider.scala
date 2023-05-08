package fr.valle.report_generator

import domain.model.ReceptionReportData

object TestDataProvider {
  val nombreSorbonnes1: String = "2"
  val mois1: String = "Janvier"
  val annee1: String = "2023"
  val nomEtablissement1: String = "Université Sorbonne Paris Nord"
  val ville1: String = "Villetaneuse"
  val departement1: String = "Seine-Saint-Denis"
  val adresse1: String = "1 Rue de la Croix Faron"
  val codePostal1: String = "93430"
  val jour1: String = "15"
  val intervenant1: String = "John Doe"
  val sexe1: String = "M"
  val societeSoutraite1: String = "FabCorp"
  val nom11: String = "Robert"
  val nom21: String = "Dupont"
  val numeroAffaire1: String = "ABC123"

  val nombreSorbonnes2: String = "1"
  val mois2: String = "Février"
  val annee2: String = "2023"
  val nomEtablissement2: String = "Sorbonne Université"
  val ville2: String = "Paris"
  val departement2: String = "Paris"
  val adresse2: String = "4 Place Jussieu"
  val codePostal2: String = "75005"
  val jour2: String = "23"
  val intervenant2: String = "Jane Smith"
  val sexe2: String = "F"
  val societeSoutraite2: String = "CoolCorp"
  val nom12: String = "Martin"
  val nom22: String = "Chen"
  val numeroAffaire2: String = "XYZ789"

  def provideReceptionReportData_1: ReceptionReportData = ReceptionReportData(nombreSorbonnes = nombreSorbonnes1, mois = mois1, annee = annee1,
    nomEtablissement = nomEtablissement1, ville = ville1, departement = departement1, adresse = adresse1,
    codePostal = codePostal1, jour = jour1, intervenant = intervenant1, sexe = sexe1, societeSoutraite = societeSoutraite1,
    nom1 = nom11, nom2 = nom21, numeroAffaire = numeroAffaire1)

  def provideReceptionReportData_2: ReceptionReportData = ReceptionReportData(nombreSorbonnes = nombreSorbonnes2, mois = mois2, annee = annee2,
    nomEtablissement = nomEtablissement2, ville = ville2, departement = departement2, adresse = adresse2,
    codePostal = codePostal2, jour = jour2, intervenant = intervenant2, sexe = sexe2, societeSoutraite = societeSoutraite2,
    nom1 = nom12, nom2 = nom22, numeroAffaire = numeroAffaire2)

  def provideReceptionReportData_1MapValues: Map[String, String] = Map(
    "#nombre_sorbonne#" -> nombreSorbonnes1,
    "#mois#" -> mois1,
    "#annee#" -> annee1,
    "#nom_etablissement#" -> nomEtablissement1,
    "#ville#" -> ville1,
    "#departement#" -> departement1,
    "#adresse#" -> adresse1,
    "#code_postal#" -> codePostal1,
    "#jour#" -> jour1,
    "#intervenant#" -> intervenant1,
    "#sexe#" -> sexe1,
    "#societe_soutraite#" -> societeSoutraite1,
    "#nom1#" -> nom11,
    "#nom2#" -> nom21,
    "#numero_d_affaire#" -> numeroAffaire1
  )

}

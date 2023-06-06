val appVersion = "2.0.0"
val appName = "Report-Generator"

ThisBuild / version := appVersion

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := appName,
    idePackagePrefix := Some("fr.valle.report_generator"),
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.15" % "test",
      "com.github.tototoshi" %% "scala-csv" % "1.3.10",
      "org.scalafx" %% "scalafx" % "17.0.1-R26",
      "org.openjfx" % "javafx-controls" % "19.0.2.1",
      "org.openjfx" % "javafx-media" % "19.0.2.1",
      "org.apache.poi" % "poi-ooxml" % "5.2.2",
      "org.apache.logging.log4j" % "log4j-core" % "2.20.0",
      "org.apache.logging.log4j" %% "log4j-api-scala" % "12.0"
    )
  )

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs@_*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

assemblyJarName := appName + "-" + appVersion + ".jar"

mainClass := Some("fr.valle.report_generator.MainReportGeneratorJFXApp")
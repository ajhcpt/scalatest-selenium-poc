name := "scalatest-selenium-poc"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.11" % "2.2.6",
  "org.scalacheck" %% "scalacheck" % "1.13.0" % "test",
  "org.seleniumhq.selenium" % "selenium-java" % "2.53.0",
  "org.scala-lang" % "scala-reflect" % "2.11.8",
  "junit" % "junit" % "4.12"
)

fork := true

javaOptions += "-Dwebdriver.chrome.driver=drivers/chromedriver.exe"
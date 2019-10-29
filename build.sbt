import sbt._

val name = "hmrc-mongo"

lazy val library = Project(name, file("."))
  .enablePlugins(SbtAutoBuildPlugin, SbtGitVersioning, SbtArtifactory)
  .settings(
    publish := {},
    publishAndDistribute := {},
    majorVersion := 0,
    scalaVersion := "2.11.12",
    crossScalaVersions := Seq("2.11.12", "2.12.8")
  )
  .aggregate(hmrcMongoPlay26, hmrcMongoPlay27, hmrcMongoTest)

lazy val hmrcMongoPlay26 = Project("hmrc-mongo-play-26", file("hmrc-mongo-play-26"))
  .enablePlugins(SbtAutoBuildPlugin, SbtArtifactory)
  .dependsOn(hmrcMongoTest % Test)
  .settings(
    majorVersion := 0,
    libraryDependencies ++= AppDependencies.hmrcMongoPlay26,
    resolvers := Seq(
      Resolver.bintrayRepo("hmrc", "releases"),
      Resolver.typesafeRepo("releases")
    )
  )

lazy val hmrcMongoPlay27 = Project("hmrc-mongo-play-27", file("hmrc-mongo-play-27"))
  .enablePlugins(SbtAutoBuildPlugin, SbtArtifactory)
  .settings(
    majorVersion := 0,
    unmanagedSourceDirectories in Compile += baseDirectory.value / "../hmrc-mongo-play-26/src/main/scala",
    libraryDependencies ++= AppDependencies.hmrcMongoPlay27,
    resolvers := Seq(
      Resolver.bintrayRepo("hmrc", "releases"),
      Resolver.typesafeRepo("releases")
    )
  )

lazy val hmrcMongoTest = Project("hmrc-mongo-test", file("hmrc-mongo-test"))
  .enablePlugins(SbtAutoBuildPlugin, SbtArtifactory)
  .settings(
    majorVersion := 0,
    libraryDependencies ++= AppDependencies.hmrcMongoTest,
    resolvers := Seq(
      Resolver.bintrayRepo("hmrc", "releases"),
      Resolver.typesafeRepo("releases")
    )
  )

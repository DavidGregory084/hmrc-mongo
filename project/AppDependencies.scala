import sbt._

object AppDependencies {

  private val play26Version = "2.6.25"
  private val play27Version = "2.7.4"

  lazy val test: Seq[ModuleID] = Seq(
    "org.scalatest"        %% "scalatest"                % "3.1.0"        % Test,
    "org.scalacheck"       %% "scalacheck"               % "1.14.3"       % Test,
    "org.scalatestplus"    %% "scalatestplus-scalacheck" % "3.1.0.0-RC2"  % Test,
    "com.vladsch.flexmark" % "flexmark-all"              % "0.35.10"      % Test,
    "ch.qos.logback"       % "logback-classic"           % "1.2.3"        % Test,
  )

  lazy val mongoCommon: Seq[ModuleID] = Seq(
    "org.mongodb.scala" %% "mongo-scala-driver" % "2.8.0",
    "org.slf4j"         %  "slf4j-api"          % "1.7.30"
  )

  lazy val metrixCommon: Seq[ModuleID] =
    Seq("io.dropwizard.metrics" % "metrics-graphite" % "3.2.6")

  lazy val hmrcMongoPlay26: Seq[ModuleID] = Seq(
    "com.typesafe.play" %% "play"       % play26Version,
    "com.typesafe.play" %% "play-guice" % play26Version,
  ) ++ mongoCommon ++ test

  lazy val hmrcMongoPlay27: Seq[ModuleID] = Seq(
    "com.typesafe.play" %% "play"       % play27Version,
    "com.typesafe.play" %% "play-guice" % play27Version,
  ) ++ mongoCommon ++ test

  lazy val hmrcMongoCachePlay26: Seq[ModuleID] = Seq() ++ mongoCommon ++ test

  lazy val hmrcMongoTestPlay26: Seq[ModuleID] = Seq(
    "com.vladsch.flexmark"  %  "flexmark-all"    % "0.35.10",
    "org.scalatest"         %% "scalatest"       % "3.1.0",
    "org.mockito"           %% "mockito-scala"   % "1.10.1" % Test
  ) ++ mongoCommon ++ hmrcMongoPlay26 ++ test

  lazy val hmrcMongoTestPlay27: Seq[ModuleID] = Seq(
    "com.vladsch.flexmark"  %  "flexmark-all"    % "0.35.10",
    "org.scalatest"         %% "scalatest"       % "3.1.0",
    "org.mockito"           %% "mockito-scala"   % "1.10.1" % Test
  ) ++ mongoCommon ++ hmrcMongoPlay27 ++ test

  lazy val hmrcMongoMetrixPlay26: Seq[ModuleID] = Seq(
    "com.kenshoo"           %% "metrics-play"    % "2.6.19_0.7.0",
    "org.mockito"           %% "mockito-scala"   % "1.10.1" % Test
  ) ++ hmrcMongoPlay26 ++ metrixCommon

  lazy val hmrcMongoMetrixPlay27: Seq[ModuleID] = Seq(
    "com.kenshoo"           %% "metrics-play"    % "2.7.3_0.8.1",
    "org.mockito"           %% "mockito-scala"   % "1.10.1" % Test
  ) ++ hmrcMongoPlay27 ++ metrixCommon
}

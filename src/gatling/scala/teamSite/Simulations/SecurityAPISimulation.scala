package teamSite.Simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import teamSite.Commons.BaseClass

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class SecurityAPISimulation extends BaseClass {

  val editorDesktop =
    scenario("Editor Desktop Client") // A scenario is a chain of requests and pauses
      .exec(
        http("Editor Desktop")
          .get("/clients/client.editor.desktop")
      )
      .pause(2); // Note that Gatling has recorder real time pauses

  val editorPlugin =
    scenario("Editor Plugin Client") // A scenario is a chain of requests and pauses
      .exec(
        http("Editor Plugin")
          .get("/clients/app.editor.plugin")
      )
      .pause(2);

  val baseballClient =
    scenario("Baseball Client") // A scenario is a chain of requests and pauses
      .exec(
        http("Baseball")
          .get("/clients/client.baseball")
      )
      .pause(2);

  val loggerDashboard =
    scenario("Logger Dashboard") // A scenario is a chain of requests and pauses
      .exec(
        http("Logger Dashboard")
          .get("/clients/client.loggerdashboard")
      )
      .pause(2);

  val teamLogger =
    scenario("Team Logger") // A scenario is a chain of requests and pauses
      .exec(
        http("Team Logger")
          .get("/clients/client.teamlogger")
      )
      .pause(2);

    setUp(
      editorDesktop.inject(rampUsers(2000).during(30 seconds)),
//      editorPlugin.inject(rampUsers(500).during(100 seconds)),
      baseballClient.inject(rampUsers(2000).during(30 seconds)),
      loggerDashboard.inject(rampUsers(2000).during(30 seconds)),
      teamLogger.inject(rampUsers(2000).during(30 seconds)),
    ).protocols(httpProtocol)
}

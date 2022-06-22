package teamSite.Simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import teamSite.Commons.BaseClass

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class GameDetailsSimulation extends BaseClass {

  val gameDetailsPage =
    scenario("GameDetails")
      .exec(
        http("GameDetails")
          .post("/reports")
          .body(
            RawFileBody("payload.json")
          )
          .check(status.is(200))
      )

  setUp(
    gameDetailsPage.inject(constantConcurrentUsers(10).during(10 seconds)).protocols(httpProtocol)
  )
}
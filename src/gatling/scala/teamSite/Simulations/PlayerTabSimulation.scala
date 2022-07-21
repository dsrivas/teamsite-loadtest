package teamSite.Simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import teamSite.Commons.BaseClass

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class PlayerTabSimulation extends BaseClass {

  val playerBoxScore =
    scenario("Player with Box Score") // A scenario is a chain of requests and pauses
      .feed(league)
      .exec(
        http("PlayersWithBoxScore")
          .get("/leagues/${leagueId}/seasons/${seasonId}/playerswithboxscore?sort=lastName&skip=0&take=25")
      )
      .pause(2); // Note that Gatling has recorder real time pauses

  var bulkGames =
    scenario("Bulk Games")
      .feed(league)
      .exec(
        http("BulkGames")
          .post("/games/bulk/get")
          .body(
            RawFileBody("games.json")
          )
      )
      .pause(2)

  var bulkTeams =
    scenario("Bulk Teams")
      .feed(league)
      .exec(
        http("BulkTeams")
          .post("/teams/bulk/get")
          .body(
            RawFileBody("teams.json")
          )
          .check(status.is(200))
      )

  setUp(
    playerBoxScore.inject(constantConcurrentUsers(50).during(10 seconds)),
    bulkGames.inject(constantConcurrentUsers(50).during(10 seconds)),
    bulkTeams.inject(constantConcurrentUsers(50).during(10 seconds)),
  ).protocols(httpProtocol)
}
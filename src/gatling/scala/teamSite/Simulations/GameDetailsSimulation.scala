package teamSite.Simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import teamSite.Commons.{BaseClass}

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class GameDetailsSimulation extends BaseClass {

//  val leagues =
//    scenario("Leagues")
//      .exec(
//        http("Leagues")
//          .get("/leagues")
//          .check(status.is(200))
//      )

//  val sports =
//    scenario("Sports")
//      .feed(sport)
//      .exec(
//        http("GameDetails")
//          .get("/sports/${sportId}/seasons?take=2048")
//          .check(status.is(200))
//        )

  val reports =
    scenario("Reports")
      .exec(
        http("Reports")
          .post("/reports")
          .body(
            RawFileBody("payload.json")
          )
          .check(status.is(200))
      )

  val cumulativeReports =
    scenario("Cumulative Reports")
      .exec(
        http("Cumulative Reports")
          .post("/reports/pbpscombined")
          .body(
            RawFileBody("expression.json")
          )
          .check(status.is(200))
      )

  val leaderboards =
    scenario("Leaderboards")
      .feed(leaderboard)
      .exec(
        http("Leaderboards")
          .get("/leaderboards/records?league.id=${leagueId}&season.id=${seasonId}&team.id=${teamId}&player.id=${playerId}&reportType=0")
          .check(status.is(200))
      )

  val PlayersWithBoxScore =
    scenario("PlayersWithBoxScore")
      .feed(league)
      .exec(
        http("PlayersWithBoxScore")
          .get("/leagues/${leagueId}/seasons/${seasonId}/playerswithboxscore?sort=lastName&skip=0&take=25")
      )

  val bulkGames =
    scenario("Bulk Games")
      .exec(
        http("BulkGames")
          .post("/games/bulk/get")
          .body(
            RawFileBody("games.json")
          )
      )

  val bulkTeams =
    scenario("Bulk Teams")
      .exec(
        http("BulkTeams")
          .post("/teams/bulk/get")
          .body(
            RawFileBody("teams.json")
          )
          .check(status.is(200))
      )



//  setUp(
//    cumulativeReports.inject(atOnceUsers(20)),
//    cumulativeReports.inject(rampUsers(20).during(10 seconds)),
//    cumulativeReports.inject(rampUsers(15).during(10 seconds)),
//    cumulativeReports.inject(rampUsers(100).during(40 minutes))
//  ).protocols(httpProtocol)

//  setUp(
//    reports.inject(rampUsersPerSec(2).to(2).during(10.seconds)),
//    PlayersWithBoxScore.inject(rampUsersPerSec(2).to(5).during(10.seconds)),
//    leaderboards.inject(rampUsersPerSec(2).to(5).during(10.seconds)),
//    bulkGames.inject(rampUsersPerSec(2).to(5).during(10.seconds)),
//    bulkTeams.inject(rampUsersPerSec(2).to(5).during(10.seconds)),
//    cumulativeReports.inject(rampUsersPerSec(2).to(5).during(10.seconds))
//  ).protocols(httpProtocol)

  setUp(
    reports.inject(constantUsersPerSec(2).during(20.seconds)),
    PlayersWithBoxScore.inject(constantUsersPerSec(2).during(20.seconds)),
    leaderboards.inject(constantUsersPerSec(2).during(20.seconds)),
    bulkGames.inject(constantUsersPerSec(2).during(20.seconds)),
    bulkTeams.inject(constantUsersPerSec(2).during(20.seconds)),
    cumulativeReports.inject(constantUsersPerSec(1).during(20.seconds))
  ).protocols(httpProtocol)
}
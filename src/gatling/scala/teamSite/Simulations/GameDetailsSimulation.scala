package teamSite.Simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import teamSite.Commons.{BaseClass}

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class GameDetailsSimulation extends BaseClass {

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

  val cumulativeReports_Old =
    scenario("Cumulative Reports")
      .exec(
        http("Cumulative Reports")
          .post("/reports/pbpscombined")
          .body(
            RawFileBody("expression.json")
          )
          .check(status.is(200))
      )

  scenario("Cumulative Reports")
      .feed(leaderboard)
      .exec(
        http("Cumulative Reports")
          .post("/possessionreports")
          .body(StringBody("""{
                             |  "expressions": [
                             |    "play eq 1 and league eq oid(${leagueId}) and season eq oid(${seasonId})  and playactorteam eq oid(${teamId}) group playactorplayer, playactorteam",
                             |    "play eq 1 and league eq oid(${leagueId}) and season eq oid(${seasonId})  and offensiveteam eq oid(${teamId})"
                             |  ],
                             |  "includePlayByPlayStats": true
                             |}""".stripMargin)).asJson
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

  setUp(
    reports.inject(atOnceUsers(10)),
    PlayersWithBoxScore.inject(atOnceUsers(100)),
    leaderboards.inject(atOnceUsers(10)),
    bulkGames.inject(atOnceUsers(10)),
    bulkTeams.inject(atOnceUsers(10)),
    cumulativeReports_New.inject(atOnceUsers(2))
  ).protocols(httpProtocol)

}

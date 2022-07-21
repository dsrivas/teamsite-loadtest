package teamSite.Commons

import io.gatling.core.Predef.{Simulation, _}
import io.gatling.http.Predef._


class BaseClass extends Simulation {

  protected val leaderboard = csv("leaderboards.csv").eager.random
  protected val sport = csv("sports.csv").eager.random
  protected val league = csv("leagues.csv").eager.random

  protected val httpProtocol = http
    .baseUrl("https://basketball-stage.synergysportstech.com/api") // Here is the root for all relative URLs
    .contentTypeHeader("application/json")
    .userAgentHeader(
      "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0"
    )
    .authorizationHeader()
}
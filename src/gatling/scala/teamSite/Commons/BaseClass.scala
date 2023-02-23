package teamSite.Commons

import io.gatling.core.Predef.{Simulation, _}
import io.gatling.http.Predef._


class BaseClass extends Simulation {

  protected val leaderboard = csv("leaderboards.csv").eager.random
  protected val sport = csv("sports.csv").eager.random
  protected val league = csv("leagues.csv").eager.random

  protected val httpProtocol = http
//    .baseUrl("https://basketball-stage.synergysportstech.com/api") // Here is the root for all relative URLs
    .baseUrl("https://security.synergysportstech.com/api/")
    .contentTypeHeader("application/json")
    .userAgentHeader(
      "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0"
    )
    .authorizationHeader("Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IjE1RjUxREM1RTc3MkM1RUNGNjc5NEY2RThEQzI1RDY2MzBGMEVCQ0FSUzI1NiIsIng1dCI6IkZmVWR4ZWR5eGV6MmVVOXVqY0pkWmpEdzY4byIsInR5cCI6ImF0K2p3dCIsImN0eSI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2F1dGguc3luZXJneXNwb3J0c3RlY2guY29tIiwibmJmIjoxNjc3MTQ5MTU2LCJpYXQiOjE2NzcxNDkxNTYsImV4cCI6MTY3NzE1Mjc1NiwiYXVkIjpbImFwaS5jb25maWciLCJhcGkuc2VjdXJpdHkiLCJhcGkuYmFza2V0YmFsbCIsImFwaS5zcG9ydCIsImFwaS5lZGl0b3IiLCJodHRwczovL2F1dGguc3luZXJneXNwb3J0c3RlY2guY29tL3Jlc291cmNlcyJdLCJzY29wZSI6WyJvcGVuaWQiLCJhcGkuY29uZmlnIiwiYXBpLnNlY3VyaXR5IiwiYXBpLmJhc2tldGJhbGwiLCJhcGkuc3BvcnQiLCJhcGkuZWRpdG9yIiwib2ZmbGluZV9hY2Nlc3MiXSwiYW1yIjpbInB3ZCJdLCJjbGllbnRfaWQiOiJjbGllbnQuYmFza2V0YmFsbC50ZWFtc2l0ZSIsInN1YiI6IjYyMmEzNTUzZGQwN2MyZWRiYjNjMmNkZSIsImF1dGhfdGltZSI6MTY3NzA2MDc1NywiaWRwIjoibG9jYWwiLCJlbWFpbCI6ImQuc3JpdmFzdGF2QHNwb3J0cmFkYXIuY29tIiwibmFtZSI6IlNyaXZhc3RhdiBEaGFybWVuZGVyIiwic2lkIjoiMDNCMThBNDVGMzk2M0E0MUY4MjU0MzVGNDIxRkZCQjgifQ.igzGUyDsn6YJyrVK4vgXZ9jHVEUJZJGTX12MIbEK5xha2uffjhMmLXe_yBi8pKXAEOGIpZE-BEFzOgLwSPCYcu556FjdXpiomkU2QYJ6igt4RY1seIa7NMyQzLlMi75v2q9vU2BcTNipiGlv5kzgr3ou58HR5JQ86kv9RCtD4_53-NnEAYYg92loBbjGjnf8nlWwdUv9jJXMfzLxxF5FDrtUV8R7Ft8pNpRjGJBcXpTrwA9PeUcjKbcnskBR2l0g4uopjCp6Q8X7apY169JtMi30GSqTluRpTPSFLhPiy-u1mkaeVsCGu6VQOFto_lAAi6hRRwoL1E259lTjm90Ssw")
}
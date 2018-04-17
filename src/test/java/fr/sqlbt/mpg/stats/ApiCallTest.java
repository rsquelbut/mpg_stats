package fr.sqlbt.mpg.stats;

import com.monpetitgazon.*;
import fr.sqlbt.mpg.stats.core.*;
import fr.sqlbt.mpg.stats.core.League;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiCallTest {

  private Client client;
  private WebTarget http;
  private String authorization =
          "";

  @Before
  public void init() {
    this.client = ClientBuilder.newClient();
    this.http = client.target("https://api.monpetitgazon.com/");
  }

  @Test
  public void should_get_result() {
    // GIVEN
    Result expected =
        Result.create()
            .home(Compo.from(343), Score.from(0), Ranking.from(">100000"))
            .away(Compo.from(442), Score.from(0), Ranking.from(">100000"))
            .build();

    MatchResult result =
        http.path("league")
            .path("qFGFF4IizdI")
            .path("results")
            .path("1_1_1")
            .request()
            .header("authorization", authorization)
            .get(MatchResult.class);

    // WHEN
    // THEN
    TeamHome_ teamHome = result.getData().getTeamHome();
    TeamAway_ teamAway = result.getData().getTeamAway();
    assertThat(Compo.from(teamHome.getComposition())).isEqualTo(expected.getHome().getCompo());
    assertThat(Ranking.from(teamHome.getRanking())).isEqualTo(expected.getHome().getRanking());
    assertThat(Score.fromNumber(teamHome.getScore())).isEqualTo(expected.getHome().getScore());

    assertThat(Compo.from(teamAway.getComposition())).isEqualTo(expected.getAway().getCompo());
    assertThat(Ranking.from(teamAway.getRanking())).isEqualTo(expected.getAway().getRanking());
    assertThat(Score.fromNumber(teamAway.getScore())).isEqualTo(expected.getAway().getScore());
  }

  @Test
  public void should_get_leagues() {
    // GIVEN
    // WHEN
    UserDashboard result =
        http.path("user/dashboard")
            .request()
            .header("authorization", authorization)
            .get(UserDashboard.class);
    // THEN
    List<com.monpetitgazon.League> mpgLeagues = result.getData().getLeagues();
    List<League> leagues =
        mpgLeagues
            .stream()
            .map(l -> League.create().withId(l.getId()).withName(l.getName()).build())
            .collect(Collectors.toList());
    assertThat(leagues).hasSize(2);
  }

  @Test
  public void should_get_all_matchs_with_result() {
    // GIVEN
    //    https://api.monpetitgazon.com/league/qFGFF4IizdI/calendar
    LeagueId leagueId = LeagueId.from("qtleDMWJaW6");
    // WHEN
    // THEN
    int currentMatchDay = getLastPlayedDay(leagueId);

    List<MatchId> allPlayedMatch = IntStream.range(1, currentMatchDay)
                                            .mapToObj(i -> getResultsForDay(leagueId, i))
                                            .map(this::extractsMatchIds)
                                            .flatMap(Collection::stream)
                                            .collect(Collectors.toList());

    assertThat(allPlayedMatch).hasSize(90);
//    allPlayedMatch.forEach(System.out::println);
  }

  private LeagueCalendar getResultsForDay(LeagueId qFGFF4IizdI, int i) {
    return http.path("league")
               .path(qFGFF4IizdI.getId())
               .path("calendar")
               .path(String.valueOf(i))
               .request()
               .header("authorization", authorization)
               .get(LeagueCalendar.class);
  }

  private int getLastPlayedDay(LeagueId qFGFF4IizdI) {
    Results results = http.path("league")
                          .path(qFGFF4IizdI.getId())
                          .path("calendar")
                          .request()
                          .header("authorization", authorization)
                          .get(LeagueCalendar.class)
                          .getData().getResults();
    int currentMatchDay = Math.toIntExact(results.getCurrentMatchDay());
    boolean seasonIsFinished = results.getMaxMatchDay().longValue() == results.getCurrentMatchDay().longValue();
    return seasonIsFinished ? currentMatchDay + 1 : currentMatchDay;
  }

  private List<MatchId> extractsMatchIds(LeagueCalendar calendar) {
    return calendar.getData().getResults().getMatches().stream()
                   .map(Match::getId)
                   .map(MatchId::from)
                   .collect(Collectors.toList());
  }
}

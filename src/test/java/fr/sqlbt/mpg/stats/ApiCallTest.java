package fr.sqlbt.mpg.stats;

import com.monpetitgazon.*;
import fr.sqlbt.mpg.stats.core.*;
import fr.sqlbt.mpg.stats.core.League;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.*;
import java.util.List;
import java.util.stream.Collectors;

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
    Data data = result.getData();
    assertThat(Compo.from(data.getTeamHome().getComposition()))
        .isEqualTo(expected.getHome().getCompo());
    assertThat(Ranking.from(data.getTeamHome().getRanking()))
        .isEqualTo(expected.getHome().getRanking());
    assertThat(Score.fromNumber(data.getTeamHome().getScore()))
        .isEqualTo(expected.getHome().getScore());

    assertThat(Compo.from(data.getTeamAway().getComposition()))
        .isEqualTo(expected.getAway().getCompo());
    assertThat(Ranking.from(data.getTeamAway().getRanking()))
        .isEqualTo(expected.getAway().getRanking());
    assertThat(Score.fromNumber(data.getTeamAway().getScore()))
        .isEqualTo(expected.getAway().getScore());
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

}

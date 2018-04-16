package fr.sqlbt.mpg.stats;

import com.monpetitgazon.*;
import fr.sqlbt.mpg.stats.core.*;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiCallTest {

  @Test
  public void should_get_result() {
    // GIVEN
    Result expected =
        Result.create()
            .home(Compo.from(343), Score.from(0), Ranking.from(">100000"))
            .away(Compo.from(442), Score.from(0), Ranking.from(">100000"))
            .build();

    Client client = ClientBuilder.newClient();
    MatchResult result =
        client
            .target("https://api.monpetitgazon.com/league/")
            .path("qFGFF4IizdI")
            .path("results")
            .path("1_1_1")
            .request()
            .header(
                "authorization",
                "")
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
}

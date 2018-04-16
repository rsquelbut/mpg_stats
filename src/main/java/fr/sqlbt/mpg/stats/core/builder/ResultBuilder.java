package fr.sqlbt.mpg.stats.core.builder;

import fr.sqlbt.mpg.stats.core.*;

public class ResultBuilder {

  private TeamResult home;
  private TeamResult away;

  public ResultBuilder home(Compo from, Score score, Ranking ranking) {
    home = new TeamResult(from, score, ranking);
    return this;
  }

  public ResultBuilder away(Compo from, Score score, Ranking ranking) {
    away = new TeamResult(from, score, ranking);
    return this;
  }

  public Result build() {
    return new Result(home, away);
  }
}

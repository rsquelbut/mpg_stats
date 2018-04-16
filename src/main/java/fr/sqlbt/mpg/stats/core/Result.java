package fr.sqlbt.mpg.stats.core;

import fr.sqlbt.mpg.stats.core.builder.ResultBuilder;

public class Result {
  private final TeamResult home;
  private final TeamResult away;

  public Result(TeamResult home, TeamResult away) {
    this.home = home;
    this.away = away;
  }

  public static ResultBuilder create() {
    return new ResultBuilder();
  }

  public TeamResult getHome() {
    return this.home;
  }

  public TeamResult getAway() {
    return away;
  }
}

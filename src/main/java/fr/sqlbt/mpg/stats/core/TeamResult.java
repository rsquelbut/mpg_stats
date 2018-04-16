package fr.sqlbt.mpg.stats.core;

public class TeamResult {
  private final Compo compo;
  private final Score score;
  private final Ranking ranking;

  public TeamResult(Compo compo, Score score, Ranking ranking) {
    this.compo = compo;
    this.score = score;
    this.ranking = ranking;
  }

  public Compo getCompo() {
    return compo;
  }

  public Score getScore() {
    return score;
  }

  public Ranking getRanking() {
    return ranking;
  }
}

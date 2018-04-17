package fr.sqlbt.mpg.stats.core.builder;

import fr.sqlbt.mpg.stats.core.League;
import fr.sqlbt.mpg.stats.core.LeagueId;

public class LeagueBuilder {
  private LeagueId id;
  private String name;

  public LeagueBuilder withId(String id) {
    this.id = LeagueId.from(id);
    return this;
  }

  public LeagueBuilder withName(String name) {
    this.name = name;
    return this;
  }

  public League build() {
    return new League(id, name);
  }
}

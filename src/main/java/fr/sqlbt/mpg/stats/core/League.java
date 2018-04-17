package fr.sqlbt.mpg.stats.core;

import fr.sqlbt.mpg.stats.core.builder.LeagueBuilder;

import java.util.Objects;

public class League {

  private final LeagueId id;
  private final String name;

  public League(LeagueId id, String name) {
    this.id = id;
    this.name = name;
  }

  public static LeagueBuilder create() {
    return new LeagueBuilder();
  }

  public LeagueId getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    League league = (League) o;
    return Objects.equals(id, league.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}

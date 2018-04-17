package fr.sqlbt.mpg.stats.core;

import java.util.Objects;

public class LeagueId {

    private final String id;

    public LeagueId(String id) {
        this.id = id;
    }

    public static LeagueId from(String id) {
        return new LeagueId(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeagueId leagueId = (LeagueId) o;
        return Objects.equals(id, leagueId.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}

package fr.sqlbt.mpg.stats.core;

import java.util.Objects;

public class MatchId {
    private final String id;

    public MatchId(String id) {
        this.id = id;
    }

    public static MatchId from(String id) {
        return new MatchId(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchId matchId = (MatchId) o;
        return Objects.equals(id, matchId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "MatchId{" + id + '}';
    }
}

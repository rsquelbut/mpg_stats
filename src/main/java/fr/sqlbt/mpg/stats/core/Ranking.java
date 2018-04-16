package fr.sqlbt.mpg.stats.core;

import java.util.Objects;

public class Ranking {
    private final String value;

    public Ranking(String value) {
        this.value = value;
    }

    public static Ranking from(String s) {
        return new Ranking(s);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ranking compo = (Ranking) o;
        return value.equals(compo.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

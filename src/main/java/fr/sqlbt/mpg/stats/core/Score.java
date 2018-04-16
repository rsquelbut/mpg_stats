package fr.sqlbt.mpg.stats.core;

import java.util.Objects;

public class Score {
    private final int value;

    public Score(int value) {
        this.value = value;
    }

    public static Score from(int s) {
        return new Score(s);
    }

    public static Score fromNumber(Number s) {
        return new Score(Math.toIntExact(s.longValue()));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score compo = (Score) o;
        return value == compo.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

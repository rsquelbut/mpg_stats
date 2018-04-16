package fr.sqlbt.mpg.stats.core;

import java.util.Objects;

public class Compo {

  private final int value;

  public Compo(int value) {
    this.value = value;
  }

  public static Compo from(int s) {
    return new Compo(s);
  }

  public static Compo from(String composition) {
    return from(Integer.valueOf(composition));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Compo compo = (Compo) o;
    return value == compo.value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}

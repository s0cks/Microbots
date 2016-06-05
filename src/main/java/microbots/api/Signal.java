package microbots.api;

public final class Signal{
  public final String name;
  public final Object[] args;

  public Signal(String name, Object... args) {
    this.name = name;
    this.args = args;
  }
}
package microbots.api;

import java.util.Map;
import java.util.Set;

public interface IMountRegistry{
  public void registerMount(String path, IMount mount);
  public void unregisterMount(String path);
  public IMount getMount(String path);
  public Set<Map.Entry<String, IMount>> all();
}
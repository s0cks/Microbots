package microbots.api;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;

public interface IMount{
  public InputStream openRead(String path);
  public Path resolve(String path);
  public void list(String path, List<String> files);
  public boolean exists(String path);
  public boolean isDirectory(String path);
}
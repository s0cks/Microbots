package microbots.api;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.List;

public interface IFileSystem {
  public OutputStream openWrite(String path);
  public InputStream openRead(String path);
  public Path resolve(String path);
  public void list(String path, List<String> list);
  public boolean mkdir(String path);
  public boolean touch(String path);
  public boolean exists(String path);
  public boolean isDirectory(String path);
}
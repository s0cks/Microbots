package microbots.api;

import java.io.OutputStream;

public interface IWritableMount
extends IMount{
  public OutputStream openWrite(String path);
  public boolean mkdir(String path);
  public boolean touch(String path);
  public boolean rm(String path);
}
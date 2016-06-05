package microbots.common.core.fs;

import microbots.api.IMount;
import net.minecraft.util.ResourceLocation;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public final class Ext9001ResourceMount
implements IMount {
  private final Path mountDir;

  public Ext9001ResourceMount(ResourceLocation loc){
    try {
      this.mountDir = Paths.get(System.class.getResource("/assets/" + loc.getResourceDomain() + "/" + loc.getResourcePath()).toURI());
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public InputStream openRead(String path) {
    try{
      Path p = this.resolve(path);
      System.out.println(p);
      if(Files.exists(p)){
        return Files.newInputStream(p);
      } else{
        return null;
      }
    } catch(Exception e){
      throw new RuntimeException(e);
    }
  }

  @Override
  public Path resolve(String path) {
    if(path.equals("/") || path.equals(this.mountDir.getFileName().toString())) return this.mountDir;
    if(path.startsWith(this.mountDir.getFileName().toString())) path = path.replace(this.mountDir.getFileName().toString() + "/", "");
    return this.mountDir.resolve(path);
  }

  @Override
  public void list(String path, List<String> files) {

  }

  @Override
  public boolean exists(String path) {
    return Files.exists(this.resolve(path));
  }

  @Override
  public boolean isDirectory(String path) {
    return Files.isDirectory(this.resolve(path));
  }
}
package microbots.common.core.fs;

import microbots.api.IWritableMount;
import net.minecraft.client.Minecraft;
import net.minecraft.world.storage.ISaveHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public final class Ext9001UsrMount
implements IWritableMount{
  private final Path dir;

  public Ext9001UsrMount()
  throws Exception{
    ISaveHandler handler = Minecraft.getMinecraft().getIntegratedServer().worldServerForDimension(0).getSaveHandler();
    this.dir = handler.getWorldDirectory().toPath().resolve("ext9001");
    Files.createDirectories(this.dir);
    Files.createDirectories(this.dir.resolve("usr"));
    Files.createDirectories(this.dir.resolve("usr").resolve("bin"));
    Files.createDirectories(this.dir.resolve("usr").resolve("home"));
  }


  @Override
  public Path resolve(String path){
    Path ret = this.dir.resolve(path.startsWith("/usr/") ? path.substring(5): path);
    if(!Files.exists(ret)){
      ret = this.dir.resolve("usr/" + (path.startsWith("/usr/") ? path.substring(5) : path));
    }
    return ret;
  }

  @Override
  public OutputStream openWrite(String path){
    try{
      return Files.newOutputStream(this.resolve(path));
    } catch(Exception e){
      e.printStackTrace(System.err);
      return null;
    }
  }

  @Override
  public boolean mkdir(String path) {
    Path newDir = this.resolve(path);
    try {
      Files.createDirectories(newDir);
      return true;
    } catch (IOException e) {
      return false;
    }
  }

  @Override
  public boolean touch(String path) {
    Path newFile = this.resolve(path);
    try{
      Files.createFile(newFile);
      return true;
    } catch(Exception e){
      return false;
    }
  }

  @Override
  public boolean rm(String path) {
    Path file = this.resolve(path);

    if(Files.isDirectory(file)){
      return false;
    }

    try{
      Files.deleteIfExists(file);
      return true;
    } catch(Exception e){
      return false;
    }
  }

  @Override
  public InputStream openRead(String path){
    try{
      return Files.newInputStream(this.resolve(path));
    } catch(Exception e){
      return null;
    }
  }

  @Override
  public void list(String path, List<String> files) {
    try{
      Path p = this.resolve(path);

      if(!Files.exists(p)){
        throw new FileNotFoundException("Couldn't resolve " + p);
      }

      try(DirectoryStream<Path> stream = Files.newDirectoryStream(p)){
        for(Path file : stream){
          files.add(file.getFileName().toString());
        }
      }
    } catch(Exception e){
      throw new RuntimeException(e);
    }
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
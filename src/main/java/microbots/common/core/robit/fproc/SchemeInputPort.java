package microbots.common.core.robit.fproc;

import java.io.IOException;
import java.io.InputStream;

public final class SchemeInputPort
implements SchemePort{
  private final InputStream in;
  private boolean closed = false;

  public SchemeInputPort(InputStream in){
    this.in = in;
  }

  public InputStream getInput(){
    return this.in;
  }

  @Override
  public void close()
  throws IOException {
    if(this.closed) return;
    this.in.close();
    this.closed = true;
  }
}
package microbots.common.core.robit.fproc;

import java.io.IOException;
import java.io.OutputStream;

public final class SchemeOutputPort
implements SchemePort{
  private final OutputStream out;
  private boolean closed = false;

  public SchemeOutputPort(OutputStream out){
    this.out = out;
  }

  public OutputStream getOutput(){
    return this.out;
  }

  @Override
  public void close()
  throws IOException {
    if(this.closed) return;
    this.out.close();
    this.closed = true;
  }
}
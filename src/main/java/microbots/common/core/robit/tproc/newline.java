package microbots.common.core.robit.tproc;

import io.github.s0cks.mscheme.Scheme;
import io.github.s0cks.mscheme.primitives.SchemeObject;
import io.github.s0cks.mscheme.primitives.SchemeProcedure;
import microbots.common.core.Terminal;

public final class newline
extends SchemeProcedure{
  private final Terminal terminal;

  public newline(Terminal terminal){
    this.terminal = terminal;
  }

  @Override
  public SchemeObject apply(Scheme scheme, SchemeObject schemeObject) {
    this.terminal.setCursorPos(1, this.terminal.getCursorY() + 1);
    return null;
  }
}
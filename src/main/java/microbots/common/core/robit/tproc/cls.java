package microbots.common.core.robit.tproc;

import io.github.s0cks.mscheme.Scheme;
import io.github.s0cks.mscheme.primitives.SchemeObject;
import io.github.s0cks.mscheme.primitives.SchemeProcedure;
import microbots.common.core.Terminal;

public final class cls
extends SchemeProcedure{
  private final Terminal terminal;

  public cls(Terminal terminal){
    this.terminal = terminal;
  }

  @Override
  public SchemeObject apply(Scheme scheme, SchemeObject schemeObject) {
    this.terminal.clear();
    return null;
  }
}
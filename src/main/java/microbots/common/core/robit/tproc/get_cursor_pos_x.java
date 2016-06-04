package microbots.common.core.robit.tproc;

import io.github.s0cks.mscheme.Scheme;
import io.github.s0cks.mscheme.primitives.SchemeNumber;
import io.github.s0cks.mscheme.primitives.SchemeObject;
import io.github.s0cks.mscheme.primitives.SchemeProcedure;
import microbots.common.core.Terminal;

public final class get_cursor_pos_x
extends SchemeProcedure{
  private final Terminal terminal;

  public get_cursor_pos_x(Terminal terminal){
    this.terminal = terminal;
  }

  @Override
  public SchemeObject apply(Scheme scheme, SchemeObject schemeObject) {
    return new SchemeNumber(terminal.getCursorX());
  }
}
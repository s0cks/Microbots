package microbots.common.core.robit.tproc;

import io.github.s0cks.mscheme.Scheme;
import io.github.s0cks.mscheme.SchemeUtils;
import io.github.s0cks.mscheme.primitives.SchemeNumber;
import io.github.s0cks.mscheme.primitives.SchemeObject;
import io.github.s0cks.mscheme.primitives.SchemeProcedure;
import microbots.common.core.Terminal;

public final class set_cursor_pos
extends SchemeProcedure{
  private final Terminal terminal;

  public set_cursor_pos(Terminal terminal) {
    this.terminal = terminal;
  }

  @Override
  public SchemeObject apply(Scheme scheme, SchemeObject schemeObject) {
    SchemeNumber x = ((SchemeNumber) SchemeUtils.car(schemeObject));
    SchemeNumber y = ((SchemeNumber) SchemeUtils.car(SchemeUtils.cdr(schemeObject)));
    terminal.setCursorPos(((int) x.value()), ((int) y.value()));
    return null;
  }
}
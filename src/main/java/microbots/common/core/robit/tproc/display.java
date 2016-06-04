package microbots.common.core.robit.tproc;

import io.github.s0cks.mscheme.Scheme;
import io.github.s0cks.mscheme.SchemeUtils;
import io.github.s0cks.mscheme.primitives.SchemeObject;
import io.github.s0cks.mscheme.primitives.SchemeProcedure;
import io.github.s0cks.mscheme.primitives.SchemeString;
import microbots.common.core.Terminal;

public final class display
extends SchemeProcedure{
  private final Terminal terminal;

  public display(Terminal terminal){
    this.terminal = terminal;
  }

  @Override
  public SchemeObject apply(Scheme scheme, SchemeObject schemeObject) {
    SchemeString value = ((SchemeString) SchemeUtils.car(schemeObject));
    if(value.value.equals("\n")){
      terminal.setCursorPos(1, terminal.getCursorY() + 1);
    } else{
      terminal.write(value.value);
      terminal.setCursorPos(terminal.getCursorX() + value.value.length(), terminal.getCursorY());
    }
    return null;
  }
}
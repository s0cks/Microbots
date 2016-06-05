package microbots.common.core.robit.tproc;

import io.github.s0cks.mscheme.Scheme;
import io.github.s0cks.mscheme.SchemeUtils;
import io.github.s0cks.mscheme.primitives.SchemeNull;
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
    SchemeObject obj = SchemeUtils.car(schemeObject);
    if(obj instanceof SchemeString){
      SchemeString value = ((SchemeString) obj);
      this.terminal.write(value.value);
      this.terminal.setCursorPos(this.terminal.getCursorX() + value.value.length(), this.terminal.getCursorY());
    } else if(!(obj instanceof SchemeNull) && obj != null){
      this.terminal.write(obj.toString());
      this.terminal.setCursorPos(this.terminal.getCursorX() + obj.toString().length(), this.terminal.getCursorY());
    }

    return null;
  }
}
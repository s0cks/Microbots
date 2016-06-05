package microbots.common.core.robit.oproc;

import io.github.s0cks.mscheme.Scheme;
import io.github.s0cks.mscheme.SchemeUtils;
import io.github.s0cks.mscheme.primitives.SchemeBoolean;
import io.github.s0cks.mscheme.primitives.SchemeNumber;
import io.github.s0cks.mscheme.primitives.SchemeObject;
import io.github.s0cks.mscheme.primitives.SchemeProcedure;

public final class pause
extends SchemeProcedure{
  @Override
  public SchemeObject apply(Scheme scheme, SchemeObject schemeObject) {
    SchemeNumber args = ((SchemeNumber) SchemeUtils.car(schemeObject));
    try {
      Thread.sleep(((long) args.value()));
      return SchemeBoolean.TRUE;
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
package microbots.common.core.robit.fproc;

import io.github.s0cks.mscheme.Scheme;
import io.github.s0cks.mscheme.SchemeUtils;
import io.github.s0cks.mscheme.primitives.SchemeBoolean;
import io.github.s0cks.mscheme.primitives.SchemeObject;
import io.github.s0cks.mscheme.primitives.SchemeProcedure;
import io.github.s0cks.mscheme.primitives.SchemeString;

public final class fclose
extends SchemeProcedure{
  @Override
  public SchemeObject apply(Scheme scheme, SchemeObject schemeObject) {
    SchemePort port = ((SchemePort) SchemeUtils.car(schemeObject));
    try{
      port.close();
      return SchemeBoolean.TRUE;
    } catch(Exception e){
      return new SchemeString(e.getMessage());
    }
  }
}
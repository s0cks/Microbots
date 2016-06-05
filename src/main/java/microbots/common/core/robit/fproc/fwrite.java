package microbots.common.core.robit.fproc;

import io.github.s0cks.mscheme.Scheme;
import io.github.s0cks.mscheme.SchemeUtils;
import io.github.s0cks.mscheme.primitives.SchemeBoolean;
import io.github.s0cks.mscheme.primitives.SchemeObject;
import io.github.s0cks.mscheme.primitives.SchemeProcedure;
import io.github.s0cks.mscheme.primitives.SchemeString;

import java.nio.charset.StandardCharsets;

public final class fwrite
extends SchemeProcedure{
  @Override
  public SchemeObject apply(Scheme scheme, SchemeObject schemeObject) {
    SchemeOutputPort out = ((SchemeOutputPort) SchemeUtils.car(schemeObject));
    schemeObject = SchemeUtils.cdr(schemeObject);

    SchemeObject arg = SchemeUtils.car(schemeObject);
    try{
      if(arg instanceof SchemeString){
        out.getOutput().write(((SchemeString) arg).value.getBytes(StandardCharsets.UTF_8));
      } else{
        out.getOutput().write(arg.toString().getBytes(StandardCharsets.UTF_8));
      }
      return SchemeBoolean.TRUE;
    } catch(Exception e){
      return SchemeBoolean.FALSE;
    }
  }
}
package microbots.common.core.robit.fproc;

import io.github.s0cks.mscheme.Scheme;
import io.github.s0cks.mscheme.SchemeUtils;
import io.github.s0cks.mscheme.primitives.SchemeObject;
import io.github.s0cks.mscheme.primitives.SchemeProcedure;
import io.github.s0cks.mscheme.primitives.SchemeString;

public final class fread
extends SchemeProcedure{
  @Override
  public SchemeObject apply(Scheme scheme, SchemeObject schemeObject) {
    SchemeInputPort out = ((SchemeInputPort) SchemeUtils.car(schemeObject));

    String buffer = "";
    try{
      int c;
      while((c = out.getInput().read()) != -1){
        char next = ((char) c);
        if(next == '\n'){
          return new SchemeString(buffer);
        } else{
          buffer += next;
        }
      }
      return new SchemeString(buffer);
    } catch(Exception e){
      return new SchemeString(e.getMessage());
    }
  }
}
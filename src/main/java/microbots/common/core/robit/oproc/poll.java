package microbots.common.core.robit.oproc;

import io.github.s0cks.mscheme.Scheme;
import io.github.s0cks.mscheme.SchemeUtils;
import io.github.s0cks.mscheme.primitives.SchemeBoolean;
import io.github.s0cks.mscheme.primitives.SchemeNull;
import io.github.s0cks.mscheme.primitives.SchemeNumber;
import io.github.s0cks.mscheme.primitives.SchemeObject;
import io.github.s0cks.mscheme.primitives.SchemeProcedure;
import io.github.s0cks.mscheme.primitives.SchemeString;
import microbots.api.Signal;
import microbots.common.core.robit.ServerRobit;

public final class poll
extends SchemeProcedure{
  private final ServerRobit robit;

  public poll(ServerRobit robit){
    this.robit = robit;
  }

  @Override
  public SchemeObject apply(Scheme scheme, SchemeObject args) {
    String name = ((SchemeString) SchemeUtils.car(args)).value;

    Signal signal;
    while((signal = this.robit.pollSignal()) != null){
      if(!signal.name.equals(name)) {
        this.robit.sendSignal(signal);
        continue;
      }

      SchemeObject newArgs = SchemeNull.instance;
      for(Object obj : signal.args){
        SchemeObject arg;
        if((obj instanceof String)){
          arg = new SchemeString(((String) obj));
        } else if(obj instanceof Number){
          arg = new SchemeNumber(((Number) obj).doubleValue());
        } else if(obj instanceof Boolean){
          arg = ((Boolean) obj) ? SchemeBoolean.TRUE : SchemeBoolean.FALSE;
        } else{
          arg = SchemeNull.instance;
        }
        newArgs = SchemeUtils.cons(arg, newArgs);
      }

      return newArgs;
    }

    return SchemeNull.instance;
  }
}
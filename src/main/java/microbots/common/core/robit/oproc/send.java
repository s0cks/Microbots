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

import java.util.LinkedList;
import java.util.List;

public final class send
extends SchemeProcedure{
  private final ServerRobit robit;

  public send(ServerRobit robit){
    this.robit = robit;
  }

  @Override
  public SchemeObject apply(Scheme scheme, SchemeObject args) {
    String name = ((SchemeString) (SchemeUtils.car(args))).value;
    args = SchemeUtils.cdr(args);

    List<Object> sigArgs = new LinkedList<>();
    SchemeObject arg;
    while((arg = SchemeUtils.car(args)) != SchemeNull.instance){
      if((arg instanceof SchemeString)){
        sigArgs.add(((SchemeString) arg).value);
      } else if(arg instanceof SchemeBoolean){
        sigArgs.add(((SchemeBoolean) arg).value);
      } else if(arg instanceof SchemeNumber){
        sigArgs.add(((SchemeNumber) arg).value());
      } else{
        sigArgs.add(null);
      }
    }

    this.robit.sendSignal(new Signal(name, sigArgs.toArray(new Object[sigArgs.size()])));
    return null;
  }
}
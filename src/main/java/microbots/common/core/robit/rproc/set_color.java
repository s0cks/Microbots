package microbots.common.core.robit.rproc;

import io.github.s0cks.mscheme.Scheme;
import io.github.s0cks.mscheme.SchemeUtils;
import io.github.s0cks.mscheme.primitives.SchemeNumber;
import io.github.s0cks.mscheme.primitives.SchemeObject;
import io.github.s0cks.mscheme.primitives.SchemeProcedure;
import io.github.s0cks.mscheme.primitives.SchemeSymbol;
import microbots.common.entity.EntityRobit;

public final class set_color
extends SchemeProcedure{
  private final EntityRobit robit;

  public set_color(EntityRobit robit){
    this.robit = robit;
  }

  @Override
  public SchemeObject apply(Scheme scheme, SchemeObject schemeObject) {
    SchemeNumber red = this.resolveColor(scheme, schemeObject);
    schemeObject = SchemeUtils.cdr(schemeObject);
    SchemeNumber green = this.resolveColor(scheme, schemeObject);
    schemeObject = SchemeUtils.cdr(schemeObject);
    SchemeNumber blue = this.resolveColor(scheme, schemeObject);

    int hex = (((int) red.value()) << 16) | (((int) green.value()) << 8) | ((int) blue.value());
    robit.getDataManager().set(EntityRobit.COLOR, hex);
    return new SchemeNumber(hex);
  }

  private SchemeNumber resolveColor(Scheme scheme, SchemeObject obj){
    SchemeObject car = SchemeUtils.car(obj);
    if(car instanceof SchemeNumber){
      return ((SchemeNumber) car);
    } else if(car instanceof SchemeSymbol){
      return this.resolveColor(scheme, scheme.eval(car));
    } else{
      return new SchemeNumber(255);
    }
  }
}
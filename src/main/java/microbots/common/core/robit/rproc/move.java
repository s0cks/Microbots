package microbots.common.core.robit.rproc;

import io.github.s0cks.mscheme.Scheme;
import io.github.s0cks.mscheme.SchemeUtils;
import io.github.s0cks.mscheme.primitives.SchemeNumber;
import io.github.s0cks.mscheme.primitives.SchemeObject;
import io.github.s0cks.mscheme.primitives.SchemeProcedure;
import io.github.s0cks.mscheme.primitives.SchemeString;
import microbots.common.entity.EntityRobit;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3i;

public final class move
extends SchemeProcedure{
  private final EntityRobit robit;

  public move(EntityRobit robit){
    this.robit = robit;
  }

  @Override
  public SchemeObject apply(Scheme scheme, SchemeObject args) {
    EnumFacing dir = EnumFacing.valueOf((((SchemeString) SchemeUtils.car(args)).value.toUpperCase()));
    args = SchemeUtils.cdr(args);
    double value = ((SchemeNumber) SchemeUtils.car(args)).value();
    Vec3i moveVec = dir.getDirectionVec();
    this.robit.moveEntity(moveVec.getX() * value, moveVec.getY() * value, moveVec.getZ() * value);
    return null;
  }
}
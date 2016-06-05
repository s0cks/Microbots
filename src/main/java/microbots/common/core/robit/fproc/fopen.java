package microbots.common.core.robit.fproc;

import io.github.s0cks.mscheme.Scheme;
import io.github.s0cks.mscheme.SchemeUtils;
import io.github.s0cks.mscheme.primitives.SchemeObject;
import io.github.s0cks.mscheme.primitives.SchemeProcedure;
import io.github.s0cks.mscheme.primitives.SchemeString;
import microbots.api.IFileSystem;

public final class fopen
extends SchemeProcedure{
  private final IFileSystem fileSystem;

  public fopen(IFileSystem fileSystem){
    this.fileSystem = fileSystem;
  }

  @Override
  public SchemeObject apply(Scheme scheme, SchemeObject schemeObject) {
    SchemeString path = ((SchemeString) SchemeUtils.car(schemeObject));
    schemeObject = SchemeUtils.cdr(schemeObject);
    SchemeString type = ((SchemeString) SchemeUtils.car(schemeObject));

    switch(type.value.toLowerCase()){
      case "w":{
        try{
          return new SchemeOutputPort(this.fileSystem.openWrite(path.value));
        } catch(Exception e){
          return new SchemeString(e.getMessage());
        }
      }
      case "r":{
        try{
          return new SchemeInputPort(this.fileSystem.openRead(path.value));
        } catch(Exception e){
          return new SchemeString(e.getMessage());
        }
      }
      default: return new SchemeString("Unknown fopen type: " + type.value.toLowerCase());
    }
  }
}
package microbots.common.core.robit.oproc;

import io.github.s0cks.mscheme.Environment;
import io.github.s0cks.mscheme.Scheme;
import io.github.s0cks.mscheme.SchemeParser;
import io.github.s0cks.mscheme.SchemeUtils;
import io.github.s0cks.mscheme.primitives.SchemeNull;
import io.github.s0cks.mscheme.primitives.SchemeObject;
import io.github.s0cks.mscheme.primitives.SchemeProcedure;
import io.github.s0cks.mscheme.primitives.SchemeString;
import microbots.api.IFileSystem;
import microbots.common.core.robit.fproc.SchemeInputPort;

import java.io.InputStream;

public final class eval
extends SchemeProcedure{
  private final IFileSystem fs;
  private final Scheme scheme;
  private final Environment env;

  public eval(IFileSystem fs, Scheme scheme, Environment env){
    this.fs = fs;
    this.scheme = scheme;
    this.env = env;
  }

  @Override
  public SchemeObject apply(Scheme scheme, SchemeObject schemeObject) {
    SchemeObject script = SchemeUtils.car(SchemeUtils.car(schemeObject));
    if(script instanceof SchemeString){
      SchemeString name = ((SchemeString) script);
      try(InputStream in = this.fs.openRead(this.fs.exists("/usr/bin/" + name.value + ".scm") ? "/usr/bin/" + name.value + ".scm" : "/bin" + name.value + ".scm")){
        System.out.println("Evaluating: " + name);
        return this.scheme.eval((new SchemeParser(in)).parse(), this.env);
      } catch(Exception e){
        e.printStackTrace(System.err);
        return new SchemeString(e.getMessage());
      }
    } else if(script instanceof SchemeInputPort){
      try(InputStream in = ((SchemeInputPort) script).getInput()){
        return this.scheme.eval((new SchemeParser(in)).parse(), this.env);
      } catch(Exception e){
        e.printStackTrace(System.out);
        return new SchemeString(e.getMessage());
      }
    }

    return SchemeNull.instance;
  }
}

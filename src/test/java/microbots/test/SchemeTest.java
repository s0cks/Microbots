package microbots.test;

import io.github.s0cks.mscheme.Environment;
import io.github.s0cks.mscheme.Scheme;
import io.github.s0cks.mscheme.SchemeParser;
import io.github.s0cks.mscheme.SchemeUtils;
import io.github.s0cks.mscheme.primitives.SchemeNull;
import io.github.s0cks.mscheme.primitives.SchemeObject;
import io.github.s0cks.mscheme.primitives.SchemeProcedure;
import io.github.s0cks.mscheme.primitives.SchemeString;
import io.github.s0cks.mscheme.primitives.SchemeSymbol;
import org.junit.Test;

public final class SchemeTest{
  @Test
  public void test()
  throws Exception{
    Scheme scheme = new Scheme();
    Environment env =new Environment(SchemeNull.instance, SchemeNull.instance, Scheme.GLOBAL);
    env.define(new SchemeSymbol("display"), new SchemeProcedure() {
      @Override
      public SchemeObject apply(Scheme scheme, SchemeObject schemeObject) {
        String value = ((SchemeString) (SchemeUtils.car(schemeObject))).value;
        if(value.equals("\n")){
          System.out.println();
        } else{
          System.out.print(value);
        }
        return null;
      }
    });
    scheme.eval((new SchemeParser(System.class.getResourceAsStream("/Test.scm"))).parse(), env);
  }
}
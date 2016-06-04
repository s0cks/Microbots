package microbots.common.core;

import org.junit.Test;

public class KeyboardTest {
  
  @Test
  public void testToString()
  throws Exception {
    Keyboard kb = new Keyboard(256);
    System.out.println(kb);
  }
}
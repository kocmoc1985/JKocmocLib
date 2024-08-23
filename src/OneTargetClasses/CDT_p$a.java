/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OneTargetClasses;

/**
 *
 * @author KOCMOC
 */
final class CDT_p$a
implements
  Runnable
{
  private int a;
  private boolean b;
  private CDT_p c;

  CDT_p$a(CDT_p c1) {
    c = c1;
    a = 0;
    b = true;
  }

  public final void run() {
    if (CDT_p$a.a(4, 1, "d") == 1) {
      a(CDT_p$a.a(3600000, 420000, "c"));
    }
    for (;;) {
      a(CDT_p$a.a(41000, 2000, "a"));
      if (BOUT__AD == false) {
        BOUT__AD = 1;
        a = a + 1;
        continue;
      }
      if (BOUT__AD) {
        BOUT__AD = 0;
        if (a == 807) {
          a = 0;
          a(CDT_p$a.a(5400000, 420000, "b"));
        }
      }
    }
  }

  synchronized private void a(int i1) {
    InterruptedException i1;
    try {
      wait((long) i1);
    }
    return;
    catch (i1) {
      java.util.logging.Logger.getLogger(CDT_p.getName()).log(SEVERE, null, i1);
      return;
    }
  }

  static private int a(int i1, int i2, String s3) {
    Random r4;
    i1 = (r4 = new java.util.Random()).nextInt(i1 - i2) + i2;
    out.println(new StringBuilder("rst: ").append(i1).append(" / ").append(s3).toString());
    return i1;
  }
}

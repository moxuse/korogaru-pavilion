/*

KrogaruPavilion.sc

Supercollider Application For KOROGARU PAVILION YCAM
Wrten by Koichiro Mori @ moxuss.org 2013

*/


/*

KrogaruPavilion Classes

*/

KPPole {

  *size {
    ^216;
  }

  *rgbSize {
    ^72;
  }

  *head {
    ^156;
  }

  *heads {
    ^(156,168..361);
  }

  *tail {
    ^371;
  }

  *tails {
    ^(156,168..361)+11;
  }

  *colorTail {
    ^369;
  }

}


KPFlex {

  *size {
    ^150;
  }

  *rgbSize {
    ^50;
  }

  *head {
    ^0;
  }

  *tail {
    ^149;
  }

  *colorTail {
    ^46;
  }

}

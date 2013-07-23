/*

firstScene.sc

Supercollider Application For KOROGARU PAVILION TCAM
Wrten by Koichiro Mori @ moxuss.org 2013

*/

"Load Firrst Scene".postln;


Tdef(\firsScene_sub,{
  "this is [][][][][][]][] first scene".postln;
  1.5.wait;
});


Tdef(\firstScene,{
  (Date.getDate.asString + " started scene _first Scene").postln;
  1.do{
    Tdef(\firsScene_sub).embed;
    Tdef(\firsScene_sub).embed
  }
});

/////////////////////////////




Tdef(\secondScene_sub,{
  "------------------------------- second scene".postln;
  0.5.wait;
});


Tdef(\secondScene,{
  (Date.getDate.asString + " started scene _second Scene").postln;
  1.do{
    Tdef(\secondScene_sub).embed;
    Tdef(\secondScene_sub).embed
  }
});
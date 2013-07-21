/*

firstScene.sc

Supercollider Application For KOROGARU PAVILION TCAM
Wrten by Koichiro Mori @ moxuss.org 2013

*/

"Load Firrst Scene".postln;


Tdef(\firsScene_sub,{
  "this is first scene".postln;
  0.25.wait;
});


Tdef(\firstScene,{
  (Date.getDate.asString + " started scene _testScene").postln;
  1.do{
    Tdef(\firsScene_sub).embed;
    Tdef(\firsScene_sub).embed
  }
});

/////////////////////////////



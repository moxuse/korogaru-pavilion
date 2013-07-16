/*

testScene.sc

Supercollider Application For KOROGARU PAVILION TCAM
Wrten by Koichiro Mori @ moxuss.org 2013

*/

"this is test Scene".postln;


Tdef(\testScene_subA,{
  "testScene__subA__dayo".postln;
  0.25.wait;
});

Tdef(\testScene_subB,{
  "testScene__subB__dayo".postln;
  s.sendMsg(9, "simplePlayer", s.nextNodeID, 0, 1, \out, 0);  // should add tail:0 to group:1
  0.25.wait;
});

Tdef(\testScene,{
  (Date.getDate.asString + " started scene _testScene").postln;
  10.do{
    Tdef(\testScene_subA).embed;
    Tdef(\testScene_subB).embed
  }
});

////////////////////

Tdef(\testScene_sub2A,{
  50.do({
    "testScene 2__subA__dayo".postln;
    0.05.wait;
  })
});


Tdef(\testScene_sub2B,{
  50.do{
    "testScene 2__subB__dayo".postln;
    0.1.wait;
  }
});

Tdef(\testScene2,{
  1.do{
    (Date.getDate.asString + " started scene _testScene2").postln;
    Tdef(\testScene_sub2A).embed;
    Tdef(\testScene_sub2B).embed
  }
})


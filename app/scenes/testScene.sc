/*

testScene.sc

Supercollider Application For KOROGARU PAVILION TCAM
Wrten by Koichiro Mori @ moxuss.org 2013

*/

"this is test Scene".postln;

Tdef(\testScene_subA,{
  //"testScene__subA__dayo".postln;
  3.do{
    var newCue;
    var newColorCue;

    // sound
    14.do{arg i;
      i.postln;
      s.sendMsg(9, "simplePlayer", s.nextNodeID, 0, 1, \out, i);  // should add tail:0 to group:1
    };

    Synth.new(\click);
    0.0125.wait;
    "fade start".postln;
    g = DMXCue.new();
    (0,1..511).do({|i| g.put(i, 0.0)});

    newCue = DMXRGBCue.new();
    newCue.range(0,509,Color(1.0, 1.0, 1.0),1);
    ~mainCue.merge(newCue);
    ~mainDMX.currentCue_(~mainCue);

    //NetAddr("localhost",5000).sendMsg("/dmx",g.value.asRawInt8);
    newColorCue = DMXRGBCue.new();
    newColorCue.range(0,509,Color(1, 0.375625, 0.001),1);

    "fade end".postln;
    //d.fade(g,2.0,'linear',0.08);
    ~mainDMX.fadeOSC(~netAddr, newColorCue, 8.8, 10);
    10.0.wait;
  };

  0.25.wait;
});

Tdef(\testScene_subB,{
  //"testScene__subB__dayo".postln;
  s.sendMsg(9, "simplePlayer", s.nextNodeID, 0, 1, \out, 0);  // should add tail:0 to group:1
  0.5.wait;
});

Tdef(\testScene,{
  (Date.getDate.asString + " started scene _testScene").postln;
  1.do{
    Tdef(\testScene_subA).embed;
    Tdef(\testScene_subB).embed
  }
});

////////////////////

Tdef(\testScene_sub2A,{
  5.do({
    "testScene 2__subA__dayo".postln;
    0.05.wait;
  })
});


Tdef(\testScene_sub2B,{
 4.do{
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
});







/*
randomRGB
*/

Tdef(\randomRGB,{

  loop{
    var newCue;
    newCue = DMXRGBCue.new();
    (509/3).do({|i|
      newCue.put(i*3, [Color(0, 0, 0),Color(1.0, 1.0, 1.0),Color(1.0, 1.0, 1.0),Color(0.0, 0, 1.0),Color(0.0, 1.0, 0),Color(1.0, 0, 0)].choose);
    });
    ~mainCue.merge(newCue);
    ~netAddr.sendMsg("/dmx", ~mainCue.asRawInt8);
    0.05.wait;
  };
});
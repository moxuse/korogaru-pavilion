/*

testScene.sc

Supercollider Application For KOROGARU PAVILION TCAM
Wrten by Koichiro Mori @ moxuss.org 2013

*/

"this is test Scene".postln;

Tdef(\testScene_subA, {
  var timeSpan = 4;

  2.do{
    var newCue;
    var newColorCue, newColorCueFlex;

    // sound
    14.do{arg i;
      s.sendMsg(9, "simplePlayer", s.nextNodeID, 0, 1, \out, i);  // should add tail:0 to group:1
    };

    Synth.new(\click);
    0.0125.wait;
    "fade start".postln;

    newCue = DMXRGBCue.new();
    newCue.range(KPPole.head,KPPole.tail,Color(1.0, 1.0, 1.0),1);


    ~mainCueP.merge(newCue);
    ~mainDMXP.currentCue_(~mainCueP);

    newColorCue = DMXRGBCue.new();
    newColorCue.range(KPPole.head,KPPole.tail,Color(1.0, 0.0, 0),1);

    "fade end".postln;
    ~mainDMXP.fadeOSC(~netAddrP, newColorCue, timeSpan, 10);

    (timeSpan+0.2).wait;
  };

  0.25.wait;
});


Tdef(\testScene, {
  (Date.getDate.asString + " started scene _testScene").postln;
  1.do{
    Tdef(\testScene_subA).embed
  }
});

////////////////////


Tdef(\testScene2, {
  1.do{
    (Date.getDate.asString + " started scene _testScene2").postln;
    Tdef(\randomRGB).embed;
    Tdef(\flexWave).embed;
  }
});




/*
randomRGB
*/

Tdef(\randomRGB, {
  var count = 30;
  "randomRGB".postln;
  count.do{
    var newCue;
    newCue = DMXRGBCue.new();
    KPFlex.rgbSize.do({|i|
      newCue.put(i*3, [Color(0, 0, 0),Color(1.0, 1.0, 1.0),Color(1.0, 1.0, 1.0),Color(0.0, 0, 1.0),Color(0.0, 1.0, 0),Color(1.0, 0, 0)].choose);
    });
    ~mainCueF.merge(newCue);
    ~netAddrF.sendMsg("/dmx", ~mainCueF.asRawInt8);
    0.05.wait;
  };
});


/*
FlexWave
*/

Tdef(\flexWave, {
  var timeSpan = 130; // 0~250
  timeSpan.do{|i|
    var newCue,sin1, sin2;
    if(KPFlex.size<i,{i = 0});
    sin1 = (sin(2pi/KPFlex.size*i).abs).clip(0,1.0);
    newCue = DMXRGBCue.new();
    KPFlex.rgbSize.do{|j|
      newCue.put(j * 3,Color(sin1 ,sin1 ,sin1));
      //sin2.postln;
    };
    ~mainCueF.merge(newCue);
    ~netAddrF.sendMsg("/dmx", ~mainCueF.asRawInt8);
    0.02.wait;
  }
});



/*

AtomicScene.sc

Supercollider Application For KOROGARU PAVILION TCAM
Wrten by Koichiro Mori @ moxuss.org 2013

*/

"this is Atomic Scene".postln;


Tdef(\atomicScene, {
  (Date.getDate.asString + " started scene Atomic Scene").postln;
  8.do{
    Tdef(\randomRGBPole).embed;
    Tdef(\randomRGBPoleReverse).embed;
  }
});



/*
RandomRGBPole
*/

Tdef(\randomRGBPole, {

  var count = 100;
  var poleNum = KPPole.rgbSize+53;
  var waitTime = 1.001;
  count.do{|i|
    var newCue;
    waitTime = 1.04 * waitTime;
    newCue = DMXRGBCue.new();
    poleNum.do({|i|
      newCue.put(i*3, [Color(0, 0, 0),Color(1.0, 1.0, 1.0),Color(1.0, 1.0, 1.0),Color(0.0, 0, 1.0),Color(0.0, 1.0, 0),Color(1.0, 0, 0)].choose);
    });
    ~mainCueP.merge(newCue);
    ~netAddrP.sendMsg("/dmx", ~mainCueP.asRawInt8);
    (1/waitTime).wait;
  };

});

Tdef(\randomRGBPoleReverse, {

  var count = 100;
  var poleNum = KPPole.rgbSize+53;
  var waitTime = 50;
  count.do{|i|
    var newCue;
    waitTime = 0.96 * waitTime;
    newCue = DMXRGBCue.new();
    poleNum.do({|i|
      newCue.put(i*3, [Color(0, 0, 0),Color(1.0, 1.0, 1.0),Color(1.0, 1.0, 1.0),Color(0.0, 0, 1.0),Color(0.0, 1.0, 0),Color(1.0, 0, 0)].choose);
    });
    ~mainCueP.merge(newCue);
    ~netAddrP.sendMsg("/dmx", ~mainCueP.asRawInt8);
    (1/waitTime).wait;
  };

});



///////////////////////////////

//Flex

///////////////////////////////


Tdef(\atomicSceneFlex, {
  (Date.getDate.asString + " started scene Atomic Scene").postln;
  8.do{
    Tdef(\randomRGBFlex).embed;
    Tdef(\randomRGBFlexReverse).embed;
  }
});


/*
RandomRGBFlex
*/

Tdef(\randomRGBFlex, {

  var count = 100;
  var poleNum = KPFlex.rgbSize+53;
  var waitTime = 1.001;
  count.do{|i|
    var newCue;
    waitTime = 1.006 * waitTime;
    newCue = DMXRGBCue.new();
    poleNum.do({|i|
      newCue.put(i*3, [Color(0, 0, 0),Color(1.0, 1.0, 1.0),Color(1.0, 1.0, 1.0),Color(0.0, 0, 1.0),Color(0.0, 1.0, 0),Color(1.0, 0, 0)].choose);
    });
    ~mainCueF.merge(newCue);
    ~netAddrF.sendMsg("/dmx", ~mainCueF.asRawInt8);
    (1/waitTime).wait;
  };

});

Tdef(\randomRGBFlexReverse, {

  var count = 100;
  var poleNum = KPFlex.rgbSize+53;
  var waitTime = 50;
  count.do{|i|
    var newCue;
    waitTime = 0.9994 * waitTime;
    newCue = DMXRGBCue.new();
    poleNum.do({|i|
      newCue.put(i*3, [Color(0, 0, 0),Color(1.0, 1.0, 1.0),Color(1.0, 1.0, 1.0),Color(0.0, 0, 1.0),Color(0.0, 1.0, 0),Color(1.0, 0, 0)].choose);
    });
    ~mainCueF.merge(newCue);
    ~netAddrF.sendMsg("/dmx", ~mainCueF.asRawInt8);
    (1/waitTime).wait;
  };

});

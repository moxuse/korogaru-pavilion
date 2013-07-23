/*

AtomicScene.sc

Supercollider Application For KOROGARU PAVILION TCAM
Wrten by Koichiro Mori @ moxuss.org 2013

*/

"this is Atomic Scene".postln;


Tdef(\atomicScene, {
  (Date.getDate.asString + " started scene Atomic Scene").postln;
  10.do{
    Tdef(\randomRGBPole).embed;
    Tdef(\randomRGBPoleReverse).embed;
  }
});



/*
andomRGBPole
*/

Tdef(\randomRGBPole, {

  var count = 100;
  var poleNum = KPPole.rgbSize+53;
  var waitTime = 1.001;
  count.do{|i|

    var newCue;

    waitTime = 1.04 * waitTime;
    waitTime.postln;
    i.postln;
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
    waitTime.postln;
    i.postln;
    newCue = DMXRGBCue.new();
    poleNum.do({|i|
      newCue.put(i*3, [Color(0, 0, 0),Color(1.0, 1.0, 1.0),Color(1.0, 1.0, 1.0),Color(0.0, 0, 1.0),Color(0.0, 1.0, 0),Color(1.0, 0, 0)].choose);
    });
    ~mainCueP.merge(newCue);
    ~netAddrP.sendMsg("/dmx", ~mainCueP.asRawInt8);
    (1/waitTime).wait;
  };

});



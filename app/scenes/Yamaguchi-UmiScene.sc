/*

Yamaguchi-UmiScene.sc

Supercollider Application For KOROGARU PAVILION TCAM
Wrten by Koichiro Mori @ moxuss.org 2013

*/

"this is Yamaguchi-Umi Scene".postln;

Tdef(\yamaguchi_UmiScene, {
  (Date.getDate.asString + " started scene Yamagushi Umi Scene").postln;
  8.do{
    Tdef(\fadeUmi).embed;
    Tdef(\fadeUmiRand).embed;
    Tdef(\poleUmiWave).embed;
  }
});


Tdef(\fadeUmi, {
  var timeSpan = 10;

 1.do{
    var newCue;
    var newColorCue;

    newCue = DMXRGBCue.new();
    newCue.range(KPPole.head,KPPole.tail,Color(1.0, 0.8, 0.0),1);

    ~mainCueP.merge(newCue);
    ~mainDMXP.currentCue_(~mainCueP);

    newColorCue = DMXRGBCue.new();
    newColorCue.range(KPPole.head,KPPole.tail,Color(0.0, 0.0, 1.0),1);

    ~mainDMXP.fadeOSC(~netAddrP, newColorCue, timeSpan, 9);

    (timeSpan+0.2).wait;
  };

  0.25.wait;
});


Tdef(\fadeUmiRand, {
  var timeSpan = 3;
  6.do{
    var newCue;
    var newColorCue;

    KPPole.heads.size.do{|i|
      newCue = DMXRGBCue.new();
      newCue.range(KPPole.heads[i], KPPole.tails[i], [Color(0.4, 0.6, 1.0.rand), Color(0.2, 0.4, 0.75.rand)].choose, 1);
      ~mainCueP.merge(newCue);
    };
    ~mainDMXP.currentCue_(~mainCueP);

    KPPole.heads.size.do{|i|
      newColorCue = DMXRGBCue.new();
      newColorCue.range(KPPole.heads[i], KPPole.tails[i], Color(0.6.rand, 0.3.rand, 1.0), 1);
    };

    ~mainDMXP.fadeOSC(~netAddrP, newColorCue, timeSpan, 4);

    (timeSpan+0.2).wait;
  };

  0.25.wait;
});


Tdef(\poleUmiWave, {
  var count = 12;
  var waitTime = 0.02;
  var from = Color(0.3, 1.0.rand, 1.0.rand);
  var to = Color(0.0, 0.0, 1.0.rand);
  2.do{
    count.do{|i|
      var newCue;
      var idx;
      idx = (count - i) - 1;
      newCue = DMXRGBCue.new();
      newCue.range(KPPole.head,KPPole.tail,Color(0,0,0));
      if( 3 <= idx ,{
        if( 8 < idx, {
          newCue.gradationRange(KPPole.heads[idx+6],KPPole.tails[idx+6],from,to);
        },{
            var nIdx = ( idx - 3 ) + idx;
            newCue.gradationRange(KPPole.heads[nIdx],KPPole.tails[nIdx],from,to);
            newCue.gradationRange(KPPole.heads[nIdx+1],KPPole.tails[nIdx+1],from,to);
        });
      },{
        newCue.gradationRange(KPPole.heads[idx],KPPole.tails[idx],from,to);
      });
      ~mainCueP.merge(newCue);
      ~netAddrP.sendMsg("/dmx", ~mainCueP.asRawInt8);
      waitTime.wait;
    }
  }
});


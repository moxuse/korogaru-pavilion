/*

RouletteScene.sc

Supercollider Application For KOROGARU PAVILION TCAM
Wrten by Koichiro Mori @ moxuss.org 2013

*/

"this is Roulette Scene".postln;

Tdef(\rouletteScene, {
  (Date.getDate.asString + " started scene Atomic Scene").postln;
  8.do{
    Tdef(\poleWave).embed;
    Tdef(\poleWaveReverse).embed;
  };
});


/*
PoleWave
*/

Tdef(\poleWave, {
  var count = 12;
  var waitTime = 1.004;
  var from = Color(1.0.rand, 1.0.rand, 1.0.rand);
      var to = Color(1.0.rand, 1.0.rand, 1.0.rand);
  25.do{
    count.do{|i|
      var newCue;
      var idx;

      waitTime = 1.025 * waitTime;
      if(50<waitTime ,{waitTime=50});
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
      (1/waitTime).wait;
    }
  }
});



/*
PoleWave Reverse
*/

Tdef(\poleWaveReverse, {
  var count = 12;
  var waitTime = 240;
  var from = Color(1.0.rand, 1.0.rand, 1.0.rand);
  var to = Color(1.0.rand, 1.0.rand, 1.0.rand);
  8.do{
    count.do{|i|
      var newCue;
      waitTime = 0.95 * waitTime;
      newCue = DMXRGBCue.new();
      newCue.range(KPPole.head,KPPole.tail,Color(0,0,0));
      if( 3 <= i ,{
        if( 8 < i, {
          newCue.gradationRange(KPPole.heads[i+6],KPPole.tails[i+6],from,to);
        },{
            var nIdx = ( i - 3 ) + i;

            newCue.gradationRange(KPPole.heads[nIdx],KPPole.tails[nIdx],from,to);
            newCue.gradationRange(KPPole.heads[nIdx+1],KPPole.tails[nIdx+1],from,to);
        });
      },{
        newCue.gradationRange(KPPole.heads[i],KPPole.tails[i],from,to);
      });

      ~mainCueP.merge(newCue);
      ~netAddrP.sendMsg("/dmx", ~mainCueP.asRawInt8);
       (1/waitTime).wait;
    }
  }
});

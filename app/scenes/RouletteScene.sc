/*

RouletteScene.sc

SuperCollider Application For KOROGARU PAVILION YCAM
Wrten by Koichiro Mori @ moxuss.org 2013

*/

"this is Roulette Scene".postln;

Tdef(\rouletteScene, {
  (Date.getDate.asString + " started scene Roulette Scene").postln;
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
  var synthId = [0,1].choose;
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
      if(0==synthId,{
        s.sendMsg(9, \rouletteBlip, s.nextNodeID, 0, 1, "out", (i%7/2).round, "midinote", 60+i);
        },{
        s.sendMsg(9, \rouletteMalet, s.nextNodeID, 0, 1, "out", (i%7/2).round, "midinote", 60+i);
      });
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
   var synthId = [0,1].choose;
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
       if(0==synthId,{
        s.sendMsg(9, \rouletteBlip, s.nextNodeID, 0, 1, "out", (i%7).round, "midinote", 60);
        },{
        s.sendMsg(9, \rouletteMalet, s.nextNodeID, 0, 1, "out", (i%7).round, "midinote", 60);
      });
       (1/waitTime).wait;
     }
   }
 });

////////////////////////////////////

// Flex

////////////////////////////////////
 /*
 FlexWave
 */

Tdef(\rouletteSceneFlex, {
   (Date.getDate.asString + " started scene Flex Roulette Scene").postln;
   8.do{
     Tdef(\flexWave).embed;
     Tdef(\flexWaveReverse).embed;
   };
 });


 /*
 FlexWave
 */

 Tdef(\flexWave, {
   var count = KPFlex.rgbSize;
   var waitTime = 1.004;
   var color = Color(1.0.rand, 1.0.rand, 1.0.rand);
   var synthId = [0,1].choose;
   9.do{|i|
    count.do{|j|
      var newCue = DMXRGBCue.new();
      waitTime = 1.025 * waitTime;
      newCue.range(KPFlex.head,KPFlex.tail,Color(0,0,0));
      newCue.range(j*3, j*3+3, color);
      ~mainCueF.merge(newCue);
      ~netAddrF.sendMsg("/dmx", ~mainCueF.asRawInt8);
      //("current flex: "++ i.asString).postln;
	  if(0==synthId,{
        s.sendMsg(9, \rouletteBlip, s.nextNodeID, 0, 1, "out", (j%5).round + 6, "midinote", 60+j, "amp", 0.03);
      },{
        s.sendMsg(9, \rouletteMalet, s.nextNodeID, 0, 1, "out", (j%5).round + 6, "midinote", 60+j, "amp", 0.03);
      });
      (1/waitTime).wait;
    }

   }
});

 /*
 FlexWaveReverse
 */

 Tdef(\flexWaveReverse, {
   var count = KPFlex.rgbSize;
   var waitTime = 240;
   var color = Color(1.0.rand, 1.0.rand, 1.0.rand);
   var synthId = [0,1].choose;
   2.do{|i|
    count.do{|j|
      var newCue = DMXRGBCue.new();
      waitTime = 0.95 * waitTime;
	  //waitTime.postln;
      newCue.range(KPFlex.head,KPFlex.tail,Color(0,0,0));
      newCue.range(j*3, j*3+3, color);
      ~mainCueF.merge(newCue);
      ~netAddrF.sendMsg("/dmx", ~mainCueF.asRawInt8);
	  if(0==synthId,{
		s.sendMsg(9, \rouletteBlip, s.nextNodeID, 0, 1, "out", (j%5).round + 6, "midinote", 60, "amp", 0.05);
      },{
        s.sendMsg(9, \rouletteMalet, s.nextNodeID, 0, 1, "out", (j%5).round + 6, "midinote", 60, "amp", 0.03);
      });
      //("current flex: "++ i.asString).postln;
      (1/waitTime).wait;
    }

   }
});



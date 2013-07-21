/*

testSynths.sc

Supercollider Application For KOROGARU PAVILION TCAM
Wrten by Koichiro Mori @ moxuss.org 2013

*/


/*
Master Volume Synth
*/

SynthDef("master_volume",{arg amp = 1;
  var src;
  src = In.ar((0,1..13)) * amp;
  ReplaceOut.ar(0, src);
}).store();

s.sendMsg(9, "master_volume", 6000, 1, 1); // id 6000 to master volume


////////////////

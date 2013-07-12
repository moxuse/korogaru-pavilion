/*

testSynths.sc

Supercollider Application For KOROGARU PAVILION TCAM
Wrten by Koichiro Mori @ moxuss.org 2013

*/

"this is test Synths".postln;

/*
SoundFile For Buffer Read
*/

s.sendMsg(\b_allocRead, 10, Platform.resourceDir ++ "/sounds/click_04.wav");
s.sendMsg(\b_allocRead, 14, Platform.resourceDir ++ "sounds/hh13.wav");


/*
Master Volume Synth
*/

SynthDef("master_volume",{arg amp = 1;
  var src;
  src = In.ar((0,1..13)) * amp;
  ReplaceOut.ar(0, src);
}).store();


/*
Simple SoundFile Player
*/

SynthDef("simplePlayer", {arg bufnum = 10, out = 0, amp = 1;
  var src;
  src = PlayBuf.ar(1, bufnum, 1, 1, 0, 0, 2) * amp;
  Out.ar(out, src)
}).store();


//s.sendMsg(9, "simplePlayer", s.nextNodeID, 1, 1);


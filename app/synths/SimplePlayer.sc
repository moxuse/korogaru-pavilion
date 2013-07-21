/*

testSynths.sc

Supercollider Application For KOROGARU PAVILION TCAM
Wrten by Koichiro Mori @ moxuss.org 2013

*/

"Loaded Simple Plyer Synths".postln;

/*
SoundFile For Buffer Read
*/

s.sendMsg(\b_allocRead, 10, ~appDir++"sounds/click_04.wav");


/*
Simple SoundFile Player
*/

SynthDef("simplePlayer", {arg bufnum = 10, out = 0, amp = 1;
  var src;
  src = PlayBuf.ar(1, bufnum, 1, 1, 0, 0);
  FreeSelfWhenDone.kr(src);
  Out.ar(out, src)
}).store();

////////////////



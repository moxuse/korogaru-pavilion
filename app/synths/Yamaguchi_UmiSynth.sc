/*

Yamaguchi_UmiSynth.sc

*/

"Loaded Yamaguchi_Umi Synths".postln;

s.sendMsg(\b_allocRead, 10, ~appDir++"sounds/umi_awa.wav");
s.sendMsg(\b_allocRead, 20, ~appDir++"sounds/umi_beach.wav");
s.sendMsg(\b_allocRead, 30, ~appDir++"sounds/umi_iwamizu.wav");
s.sendMsg(\b_allocRead, 40, ~appDir++"sounds/umi_kamome.wav");
s.sendMsg(\b_allocRead, 50, ~appDir++"sounds/umi_kudaku.wav");
s.sendMsg(\b_allocRead, 60, ~appDir++"sounds/umi_ryo.wav");
s.sendMsg(\b_allocRead, 70, ~appDir++"sounds/umi_sunahama.wav");



/*
Simple SoundFile Player Asr
*/

SynthDef("simplePlayerAsr", {arg bufnum = 10, out = 0, amp = 1, gate = 1;
  var src;
  src = PlayBuf.ar(1, bufnum, 1, 1, 0, loop: 1)*EnvGen.ar(Env.asr(1,0.2,2),gate,doneAction:2)*LFNoise2.ar(0.3).abs;
  FreeSelfWhenDone.kr(src);
  Out.ar(out, src)
}).store();


SynthDef("simplePlayerAsrNonRand", {arg bufnum = 10, out = 0, amp = 1, gate = 1;
  var src;
  src = PlayBuf.ar(1, bufnum, 1, 1, 0, loop: 1)*EnvGen.ar(Env.asr(1,0.2,2),gate,doneAction:2);
  Out.ar(out, src)
}).store();


SynthDef("nami_noise", {arg out = 0, amp = 1, gate = 1;
  var src;
  src = ( (BrownNoise.ar(0.05)+PinkNoise.ar(0.15)) * LFNoise2.ar(0.3) )*EnvGen.ar(Env.asr(1,1.0,2),gate,doneAction:2);
  FreeSelfWhenDone.kr(src);
  Out.ar(out, src)
}).store();
////////////////


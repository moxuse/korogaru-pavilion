/*

RouletteSynth.sc

*/

SynthDef(\rouletteBlip,{arg out=0, midinote=60, amp = 0.4;
  var osc;
  osc = Pulse.ar( midinote.midicps, 0.4, 0.3) * EnvGen.ar(Env.perc(0.0,0.05,1*amp,12),doneAction:2);
  Out.ar(out,osc);
}).store;

SynthDef(\rouletteMalet,{arg out=0, midinote=60, amp = 0.4;
  var osc;
  osc = Resonz.ar( PinkNoise.ar(350),midinote.midicps, 0.002) * EnvGen.ar(Env.perc(0.0,0.6,1*amp,-12),doneAction:2);
  Out.ar(out,osc);
}).store;

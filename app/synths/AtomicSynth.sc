/*

AtomicSynth.sc

*/


(
SynthDef(\atomicSine, {arg gate = 1, amp = 0.3, decay = 0.8;
	var demand,osc, trig;

  trig = Impulse.ar(4);

	demand = Demand.ar( trig, 0, Drand([84,89,70,73,79,75,68,56,44,35].midicps,inf) );

	osc = SinOsc.ar( demand ,0 ,0.4) *EnvGen.ar(Env.perc(0.03,0.4,1,-3),doneAction:2);

  osc = osc + Impulse.ar(1,0)*0.3;

  8.do{|i|
    Out.ar(TRand.ar(0, 13, trig).round, osc * amp);
  }
}
).store;



SynthDef(\atomicClip, {arg gate = 1, amp = 0.3, decay = 0.8;
	var osc,trig;

  trig = Impulse.ar(4);

  osc = ClipNoise.ar(0.2)*EnvGen.ar(Env.perc(0.0,0.4,1,-12),doneAction:2);


  8.do{|i|
    Out.ar(TRand.ar(0, 13, trig).round, osc * amp);
  }
}
).store;

SynthDef(\atomicKlank, {arg gate = 1, amp = 0.3, decay = 0.8;
	var osc,trig;

  trig = Impulse.ar(4);

  osc = Klank.ar(`[[800, 671, 4153, 8723], nil, [1, 1, 1, 1]], PinkNoise.ar(0.1))*EnvGen.ar(Env.perc(0.0,0.4,1,-12),doneAction:2);


  8.do{|i|
    Out.ar(TRand.ar(0, 13, trig).round, osc * amp);
  }
}
).store;
)


s.boot


ProxySpace.push(s);

~x.play;
(
{ PanAz.ar(14,DynKlank.ar(`[[60, 171, 53, 723]*Line.kr(1,1,1.3), [8, 8, 5, 5], [0.4, 0.4, 0.4, 0.4]], Dust.ar(22520,0.002)) * EnvGen.ar(Env.perc(1.1,3,1,-12)),
  0;,
  Line.kr(-1,1,0.3)
)}.play;
)

~x.source = { PanAz.ar(14,Pulse.ar( 6200, 0.5, 0.25) * EnvGen.ar(Env.perc(0.0,0.05,1,12)),1) };

~x.source = { SinOsc.ar( Demand.ar(Impulse.ar(5),0,Dseq([400, 500, 600, 700, 800], inf)), 0, 0.3) * EnvGen.ar(Env.perc(0.01,6,1,-12)) };

s.quit

s.boot



//////////////

(
~x.source = {
	var demand,osc,trig;
	trig = Dust.ar(6);

	demand = Demand.ar( trig, 0, Drand([80,98,78,75,68,56,44].midicps,inf) );

	osc = SinOsc.ar( demand ,0 ,0.2)* Decay2.ar(trig,0.01,0.8);

	4.do( { osc = CombC.ar(osc, 0.25, { [0.04.rand,0.04.rand] + 0.1 }, 0.5) } );
  6.do{|i|
    Out.ar(TRand.ar(0,13,trig).round, osc*0.2);
  }


}
)

// 2)
(
~x.source = {
	var trig;
	trig  = Dust.ar(12);
  5.do{|i|
  Out.ar(TRand.ar(0,13,trig).round,
	HPF.ar(
		ClipNoise.ar(0.1) * Decay2.ar( trig, 0.0, 0.0125 ),
		800
  )
  )
  }
}
)

/*------------------------------
1. pad
------------------------------*/
// 1)
(
~x.source = {
	var osc,klank,high,mix;
	osc = Mix.fill(12,{var freq; freq ={[58,77,82].choose.midicps};  SinOsc.ar( freq * [0.5,1,2].choose +LFNoise2.kr(0.1,5) , SinOsc.ar( freq*0.5, SinOsc.ar(freq * 0.5, 0, 0.2), 0.5), LFNoise2.kr( 0.3, 0.01 ) ) }) * Decay2.ar(Impulse.ar(10),0.01,0.3);

	high =  RLPF.ar( Mix.fill( 15, { Pulse.ar( ( [46,49,51].choose ).midicps * [1,2,3,6,9].choose + LFNoise2.kr(0.05, 3),0.25, LFNoise2.kr( 0.05, 0.1 )) }), 500);

	klank = Klank.ar( `[ [5500, 1278, 650, 93], nil, [1, 1, 0.8, 0.6]], Impulse.ar(12,0.01,0.05)!2) * LFNoise2.kr(0.3, 0.3);

	mix = klank + osc + high;

	4.do( { mix = AllpassC.ar(mix, 0.5, { [0.04.rand,0.04.rand] + 0.05 }, 0.3) } );

  14.do{|i|
    Out.ar(i,mix)
  };
}
)



// 4)
~x.fadeTime = 3.0;
(
~x.source = {
	var lo, sine, high, freq, mix, wet, mid;

	lo = DynKlang.ar(`[ [46,49,53,58].midicps, [0.5,1,0.6,1.0], [1.0,1.0,1.0,1.0] ], 0.5,0 )!2 * Decay2.ar(Dust.ar(12),0.4,1,0.05);

	mid = Mix.fill( 5, { SinOsc.ar( ( [46,49,51,53,58].choose ).midicps * [1,2,3,6].choose + LFNoise2.kr( 0.05, 3 ),0.25,  Decay2.ar(Dust.ar(12),0.8,1,0.03)) }).softclip;

	high =  Mix.fill( 5, { SinOsc.ar( ( [46,49,51,53,58].choose ).midicps * [1,2,3,6,9].choose + LFNoise2.kr( 0.05, 3 ),0.05, Decay2.ar(Dust.ar(8),1,1,0.1) ) }).softclip;

	mix = RLPF.ar( high + lo + mid, 1200 , 0.3);

	4.do( { mix = AllpassC.ar(mix, 0.3, { [0.1.rand,0.1.rand] + 0.1 }, 0.5) } );
	14.do{|i|
    Out.ar(i,mix)
  };
}
)

// 5)
(
~x.source = {
	var demand,osc;

	demand = Demand.kr( Dust.kr(0.4), 0, Dseq([0,-20],inf) );
	osc = Ringz.ar( LFNoise2.ar(190, LFNoise0.ar(0.3,0.01).abs) ,95 + demand , 30, 1.0).softclip * 0.3;

	4.do( { osc = CombC.ar(osc, 0.1, { [0.04.rand,0.04.rand] + 0.1 }, 0.5) } );
	  14.do{|i|
    Out.ar(i,osc*0.2)
  };
}
)

// 6.1)
(
~x.source = {
	var lo, mid, mod, mix, src ,klank;

	lo = DynKlang.ar(`[ [47,54,59].midicps + LFNoise2.kr( 0.1, 3 ), [0.5,1,0.6,1.0], [1.0,1.0,1.0,1.0] ], 0.5,0 )!2 * 0.1;

	mid = Mix.fill( 15, {var freq; freq =[47,54,59,80,85].choose.midicps; SinOsc.ar( freq* 9.fib.choose + LFNoise2.kr( 0.1, 3 ),LFSaw.ar(freq*6,SinOsc.ar(freq*3,0,3),0.6666),LFNoise2.ar(0.4,0.125)+0.0003) }).softclip;

	klank  = Mix.fill(10, { DynKlank.ar(`[ [47,54,59].midicps *{9.fib.choose}, [1,0.666,0.3333], [1.0.rand,0.8.rand,0.8.rand] ], WhiteNoise.ar(0.003) ) });

	src = LPF.ar( lo + mid + klank , LFNoise2.ar(0.1,20,118).midicps );

	mix = AllpassC.ar(
		src,
		0.1,
		[{0.01.rand + 0.02},{0.01.rand + 0.02}],
		0.8
	);
		  14.do{|i|
    Out.ar(i,mix)
  };
}
)
s.makeGui
~x.play
// 6.2)
(
~x.source = {
	var lo, mid, mod, mix, src ,klank;

	lo = DynKlang.ar(`[ [47,54,59].midicps + LFNoise2.kr( 0.1, 3 ), [0.5,1,0.6,1.0], [1.0,1.0,1.0,1.0] ], 0.5,0 )!2 * 0.1;

	mid = Mix.fill( 15, {var freq; freq =[47,54,59,80,85].choose.midicps; SinOsc.ar( freq* 9.fib.choose + LFNoise2.kr( 0.1, 3 ),LFSaw.ar(freq*6,SinOsc.ar(freq*3,0,3),0.6666),LFNoise2.ar(0.4,0.125)+0.0003) }).softclip;

	klank  = Mix.fill(10, { DynKlank.ar(`[ [47,54,59].midicps *{9.fib.choose}, [1,0.666,0.3333], [1.0.rand,0.8.rand,0.8.rand] ], WhiteNoise.ar(0.003) ) });

	src = LPF.ar( lo + mid + klank , LFNoise2.ar(0.1,20,118).midicps );


	mix = AllpassC.ar(
		(src * 40).softclip * 0.2,
		0.1,
		[{0.01.rand + 0.02},{0.01.rand + 0.02}],
		0.8
	);

	mix;
}
)



//////////////////////////

// asr test

SynthDef(\normalAsr,{arg gate = 1;
  Out.ar(0,
    Impulse.ar(1200,0,0.3)*EnvGen.ar(Env.asr(1,0.2,2),gate, doneAction:2)*LFNoise2.ar(0.3).abs;
  )
}).store();

s.sendMsg(9, \normalAsr, 1004, 0, 0, "gate", 1 );
s.sendMsg( "/n_set", 1004, "gate", 0)

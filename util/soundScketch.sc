s.boot


ProxySpace.push(s);

~x.play;

~x.source = { DynKlank.ar(`[[60, 3071, 4053, 12723]*Line.kr(1,1,1.3), [8, 8, 5, 5], [0.4, 0.4, 0.4, 0.4]], Dust.ar(22520,0.002)) * EnvGen.ar(Env.perc(0.01,3,1,-12)) };

~x.source = { Pulse.ar( 200 + Line.kr( 0, 120, 0.06 ), 0.3, 1) * EnvGen.ar(Env.perc(0.01,3,1,-12)) };

~x.source = { SinOsc.ar( Demand.ar(Impulse.ar(24),0,Dseq([400, 500, 600, 700, 800], inf)), 0, 0.3) * EnvGen.ar(Env.perc(0.01,6,1,-12)) };

s.quit

s.boot
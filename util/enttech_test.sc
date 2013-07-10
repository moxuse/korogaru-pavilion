d = DMX.new;
//d.maxchannels =512;

SerialPort.devices

e = EntTecDMXUSBPro.new( "/dev/tty.usbserial-EN119503" );
e = EntTecDMXUSBProMk2.new( "/dev/tty.usbserial-ENVWHYEN" );


d.device = e;
(0,1..511).do({|i| c.put(i, 0.2.rand)});
c = DMXCue.new();
f = DMXCue.new();


/*k = DMXLight(\noise);
k.setStrobe((0,1..511), (0,1..511));*/

r = Dictionary.new

r.put("noise", t);
m = r.at("noise");
m.play

x !? ( _ * 3 ) ?? { "It was a nil, so I give a default value".postln; Point(1,1) }


(
g = Pdef(\noise,
	Pseq([
	Pfunc({
	5.do({
		f = DMXCue.new(0);
				(0,1..50).do({|i| f.put(i, 0.2.rand);});

		d.currentCue = f;
		d.setCue;
		"noise".postln;
		0.1.wait; //we should not dimming faster than 0.1ms
	}, { \reset.postln })
}),

Pfunc({
	5.do({
		f = DMXCue.new(0);
		(0,1..50).do({|i| f.put(i, 0.2.rand)});

		d.currentCue = f;
		d.setCue;
		"era".postln;
		0.1.wait; //we should not dimming faster than 0.1ms
			})
}, { \reset.postln })
	],inf)
)
)

Tdef(\noise).play(SystemClock);
Tdef(\noise).stop

c = DMXCue.new
(0,1..511).do({|i| c.put(i, 0)});
(
fork{
	g = DMXRGBCue.new();
	(0,1..511).do({|i| f.put(i, 0.0)});
	g.range(35,114,Color(0.1,0.3,1.3));
	c.merge(g);
	"put".postln;
	d.currentCue = f;
	d.setCue;
	d.fade(c,1.0,\linear);
}
)

c = DMXCue.new
o = DMXSubCue.new


// send the settings of the cue to the strobe:
d.currentCue = c;
d.setCue;

// strobe every two seconds at full intensity
Pdef( 'strobing', Pbind( \dur, 2, \strobe, Pfunc({ c.put( 1, 255); d.setCue;
"i strobe".postln; }, inf) ) );
Pdef(\strobing).play;



(
SynthDef(\help_sinegrain,
    { arg out = 0, freq = 440, sustain = 0.05;
        var env;
        env = EnvGen.kr(Env.perc(0.01, sustain, 0.2), doneAction:2);
        Out.ar(out, SinOsc.ar(freq, 0, env))
    }).add;
)

//////////////////////////////////

(
var a;
a = Pfuncn({
	var g = DMXCue.new();
	(0,1..511).do({|i| g.put(i, 1.0)});
	g;
}, 14).asStream;

b = Pfuncn({
	var g = DMXCue.new();
	(0,1..511).do({|i| g.put(i, 2.0)});
	g;
}, 13).asStream;
c = Pseq([a,b],1).asStream;
{
    c.do { |val|
		val.postln;
        //Synth(\help_sinegrain, [\freq, val * 100 + 300]);
		val.data.postln;
		d.currentCue = f;
		d.setCue;
        0.05.wait;
    }
}.fork;
)

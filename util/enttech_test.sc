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

(
c = DMXCue.new();
(0,1..511).do({|i| c.put(i, 0)});
g = DMXRGBCue.new();
fork{
	(0,1..511).do({|i| c.put(i, 0.0)});
	g.range(0,127,Color(0.03221875, 0.001, 1),1);
	c.merge(g);
	"put".postln;
	d.currentCue = c;
	d.setCue;
	d.fade(c,1.0,'linear',0.05);
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
SynthDef(\click,{
	Out.ar(0,Impulse.ar(1))*EnvGen.ar(Env.perc(0,0.1,1,12),doneAction:2);
}).store()
)

d = DMX.new;
e = EntTecDMXUSBPro.new( "/dev/tty.usbserial-EN119503" );
//e = EntTecDMXUSBProMk2.new( "/dev/tty.usbserial-ENVWHYEN" );
d.device = e;
g = DMXCue.new();
c = DMXCue.new();
f = DMXCue.new();

(

~strobC = Pseq([
	Pfuncn({
		Synth.new(\click);
		(0,1..511).do({|i| g.put(i, 0.5.rand)});
		"this is a".postln;
		g;
	}, 10),
	Pfuncn({
		Synth.new(\click);
		"this is b".postln;
		(0,1..511).do({|i| g.put(i, 1.0.rand)});
		g;
	}, 10)
], 2);

~strobA = Pseq([
	Pfuncn({
		"scene A is a".postln;
		(0,1..511).do({|i| g.put(i, 0.5.rand)});
		g;
	}, 10),

	Pfuncn({
		Synth.new(\click);
		(0,1..511).do({|i| g.put(i, 1.0.rand)});
		"scene A is b".postln;
		g;
	}, 10)

],2);

)


(
z = Routine({

	loop{
    ~strobC.do {|value|
		//d.sendCue(value);
//		NetAddr("10.4.0.58",5000).sendMsg("/dmx",value.asRawInt8);
		NetAddr("localhost",5000).sendMsg("/dmx",value.asRawInt8);
        0.05.wait;
    };
	1.0.wait;

	~strobA.do {|value|
		//d.sendCue(value);
//		NetAddr("10.4.0.58",5000).sendMsg("/dmx",value.asRawInt8);
		NetAddr("localhost",5000).sendMsg("/dmx",value.asRawInt8);
        0.05.wait;
    };
	1.0.wait;

//	Synth.new(\click);
		// "fade start".postln;
		// y = DMXRGBCue.new();
		// y.range(0,511,Color(0.03221875, 0.001, 1),1);
		// g.merge(y);
		// "fade end".postln;
		// //d.fade(g,2.0,'linear',0.08);
		// d.fadeOSC(NetAddr("10.4.0.58",5000), g, 2.0,'linear',0.08);
		// 4.0.wait;

	}
}).play()
)

z.stop();


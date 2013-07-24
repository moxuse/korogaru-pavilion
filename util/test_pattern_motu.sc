/*

sound test

*/

s.options.device = "MOTU 896HD";
s.options.numOutputBusChannels_(22);

s.reboot

(
Tdef(\soundCheck,{
  inf.do{|i|
    var ch = i%14;
    {Out.ar(ch, PinkNoise.ar(0.1)*MouseY.kr(1,0))* EnvGen.ar(Env.linen(0.0,1,0.0,1),doneAction:2)}.play();
    ("current ch is__ : "++ch.asString()).postln;
    1.0.wait;
  }
}).play()
)

Tdef(\soundCheck).stop();


//A-Z Panning

(
{PanAz.ar(14, PinkNoise.ar(0.3)*MouseY.kr(1,0), MouseX.kr)}.play();
)


{Out.ar(10, PinkNoise.ar(0.3)*MouseY.kr(1,0))}.play

s.quit
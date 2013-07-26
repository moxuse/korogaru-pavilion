/*

MicRcorderSynth.sc

Supercollider Application For KOROGARU PAVILION TCAM
Wrten by Koichiro Mori @ moxuss.org 2013

*/

(

~recordBuf = Buffer.alloc(s, 44100 * 6.0, 1);

SynthDef(\vocoder,{arg gate = 1, out = 0;
  var in, chain,src;
    in = SoundIn.ar(1,1);
      chain = FFT( LocalBuf(1024)  , in);
  chain = PV_BinScramble(chain, 0.89 , LFNoise0.kr(0.23,0.1).abs, Dust.kr(0.4));
  src = Limiter.ar(IFFT(chain)*1.75,1.0);
    Out.ar(out, src * EnvGen.ar(Env.asr(1,1,3), gate, doneAction:2));
}).store;


SynthDef(\echoRecorder,{
  var in;
  in = SoundIn.ar(1,1);
  RecordBuf.ar(in, ~recordBuf.bufnum, 0, 0.5, 0.5, loop: 1);
}).store;



SynthDef(\echoPlayer,{arg gate = 1, out = 0;
  Out.ar(out, Limiter.ar( PlayBuf.ar(1, ~recordBuf.bufnum, 1, 1, 0.0, loop: 1)*1.75, 1.0 ) * EnvGen.ar(Env.asr(1,1,3), gate, doneAction:2) );
}).store;


s.sendMsg( 9, \echoRecorder, 9999, 0, 1 );

)
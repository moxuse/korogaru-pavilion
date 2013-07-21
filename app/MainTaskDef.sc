/*

MainTaskDef.sc

Supercollider Application For KOROGARU PAVILION TCAM
Wrten by Koichiro Mori @ moxuss.org 2013

*/

(
//////// main task def runner ////////

Tdef(\main,{
  inf.do{|i|
    ("scene_current : "+i+" : "+~scenes[i%~scenes.size]).postln;
    Tdef(~scenes[i%~scenes.size]).embed;

    ~refreshConsole.value(Tdef(\main).isPlaying, ~scenes[i%~scenes.size]);
    (i%~scenes.size).postln;
  }
});

//////// next scene osc function callback ///////

OSCFunc({|msg|
  msg.postln;
  "\n\n\n\n\n\n".postln;
  Tdef(\main).pause;
  Tdef(\main).source = {
   ("scene_current : "+i+" : "+~scenes[i%~scenes.size]).postln;

    inf.do{|i|
      if( i<1 ,{
        Tdef(~scenes[msg[1]]).embed;
        },{
          Tdef(~scenes[i%~scenes.size]).embed;
      });

      ~refreshConsole.value(Tdef(\main).isPlaying, ~scenes[i%~scenes.size];);
    }
  };
  Tdef(\main).resume;
  },

  '/next_scene',
  nil
);

)

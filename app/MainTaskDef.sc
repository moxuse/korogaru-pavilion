/*

MainTaskDef.sc

Supercollider Application For KOROGARU PAVILION TCAM
Wrten by Koichiro Mori @ moxuss.org 2013

*/

(
//////// main task def runner ////////

Tdef(\mainP,{

  inf.do{|i|
    ("scene_current P : "+i+" : "+~scenesP[i%~scenesP.size]).postln;
    Tdef(~scenesP[i%~scenesP.size]).embed;
    ~refreshConsole.value(Tdef(\mainP).isPlaying, ~scenesP[i%~scenesP.size]);
    (i%~scenesP.size).postln;
  };
});

Tdef(\mainF,{
  inf.do{|i|
    ("scene_current F : "+i+" : "+~scenesF[i%~scenesF.size]).postln;
    Tdef(~scenesF[i%~scenesF.size]).embed;

  };
});


//////// next scene osc function callback ///////

OSCFunc({|msg|
  msg.postln;
  "\n\n\n\n\n\n messasge recieved !!!!!!!!!!!!!!!!!!!!!!!".postln;
/*  Tdef(\mainF).pause;
  Tdef(\mainF).source = {
    ("scene_current : "+i+" : "+~scenesP[i%~scenesP.size]).postln;

    inf.do{|i|
      if( i<1 ,{
        Tdef(~scenesP[msg[1]]).embed;
        },{
          Tdef(~scenesP[i%~scenesP.size]).embed;
      });

      ~refreshConsole.value(Tdef(\mainF).isPlaying, ~scenesP[i%~scenesP.size];);
    };

  };
  Tdef(\mainF).resume;*/

  Tdef(\mainP).pause;
  Tdef(\mainP).source = {

    inf.do{|i|
      ("scene_current : "+i+" : "+~scenesP[i%~scenesP.size]).postln;
      if( i<1 ,{
        Tdef(~scenesP[0]).embed;
        },{
          Tdef(~scenesP[i%~scenesP.size]).embed;
      });

    };

  };
  Tdef(\mainP).resume;

  },

  '/next_scene',
  nil
);

)

//Tdef(\mainP).play;
//NetAddr("localhost",NetAddr.langPort).sendMsg("/next_scene");
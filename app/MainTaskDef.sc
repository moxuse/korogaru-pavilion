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
    ~globalSceneCount = ~globalSceneCount+1;
    Tdef(~scenesP[~globalSceneCount%~scenesP.size]).embed;

  };
}).play;

Tdef(\mainF,{
    inf.do{|i|
    ("scene_current F : "+i+" : "+~scenesF[i%~scenesF.size]).postln;
    ~globalSceneCount = ~globalSceneCount+1;
    Tdef(~scenesF[~globalSceneCount%~scenesF.size]).embed;

  };
}).play;

//////// next scene osc function callback ///////

OSCFunc({|msg|
  msg.postln;
  Tdef(\mainP).reset;
  "globalSceneCount--- ".post;
  ~globalSceneCount.postln;
  },
  '/next_scene',
  nil
);

)

/*

MainTaskDef.sc

Supercollider Application For KOROGARU PAVILION TCAM
Wrten by Koichiro Mori @ moxuss.org 2013

*/

(
//////// main task def runner ////////

Tdef(\mainP,{
    inf.do{|i|
    ("scene_current P : "+i+" : "+~scenesP[~globalSceneCount%~scenesP.size]).postln;
    ~globalSceneCount = ~globalSceneCount+1;
    ~refreshConsole.value(Tdef(\mainP).isPlaying, ~scenesP[~globalSceneCount%~scenesP.size]);
    Tdef(~scenesP[~globalSceneCount%~scenesP.size]).embed;

  };
}).play;

Tdef(\mainF,{
    inf.do{|i|
    ("scene_current F : "+i+" : "+~scenesF[~globalSceneCount%~scenesF.size]).postln;
    Tdef(~scenesF[~globalSceneCount%~scenesF.size]).embed;

  };
}).play;

//////// next scene osc function callback ///////

OSCFunc({|msg|
  msg.postln;
  Tdef(\mainP).reset;
  Tdef(\mainF).reset;
  "globalSceneCount--- ".post;
  ~globalSceneCount.postln;
  s.sendMsg("n_set",2001, \gate, 0);
  s.sendMsg("n_set",2002, \gate, 0);
  s.sendMsg("n_set",2003, \gate, 0);
  s.sendMsg("n_set",2004, \gate, 0);
  s.sendMsg("n_set",2005, \gate, 0);
  s.sendMsg("n_set",2006, \gate, 0);
  s.sendMsg("n_set",2007, \gate, 0);
  s.sendMsg("n_set",2008, \gate, 0);
  s.sendMsg("n_set",2009, \gate, 0);
  s.sendMsg("n_set",2010, \gate, 0);
  s.sendMsg("n_set",2011, \gate, 0);
  s.sendMsg("n_set",2012, \gate, 0);
  s.sendMsg("n_set",2013, \gate, 0);
  s.sendMsg("n_set",2014, \gate, 0);
  },
  '/next_scene',
  nil
);

)

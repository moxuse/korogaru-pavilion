/*

MainTaskDef.sc

Supercollider Application For KOROGARU PAVILION TCAM
Wrten by Koichiro Mori @ moxuss.org 2013

*/

(
//////// main task def runner ////////

Tdef(\mainP,{
    inf.do{|i|
		var fact = [0,1].choose;
		("scene_current P : "+i+" : "+~scenesP[~globalSceneCount%~scenesP.size]).postln;
		Tdef(~scenesP[~globalSceneCount%~scenesP.size]).embed;
		~globalSceneCount = ~globalSceneCount+1;
		~refreshConsole.value(Tdef(\mainP).isPlaying, ~scenesP[~globalSceneCount%~scenesP.size]);

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
		if(0==fact%2,{
			s.sendMsg(9, \vocoder, 10000, 0, 1, "gate", 1, "out", 12);
			s.sendMsg("/n_set", 10001, "gate", 0);
		},{
			s.sendMsg(9, \echoPlayer, 10001, 0, 1, "gate", 1, "out", 12);
			s.sendMsg("/n_set", 10000, "gate", 0);
		})
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
	~globalSceneCount = ~globalSceneCount+1;
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

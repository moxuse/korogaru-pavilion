/*

MainTaskDef.sc

Supercollider Application For KOROGARU PAVILION TCAM
Wrten by Koichiro Mori @ moxuss.org 2013

*/

(

//////// main task def runner ////////

Tdef(\main,{
	inf.do{|i|
    ("scene_current : "+i).postln;
		Tdef(~scenes[i%~scenes.size]).embed;
    ~currentScene = ~scenes[i%~scenes.size];
    ~refreshConsole.value(Tdef(\main).isPlaying, ~currentScene);
	}
});

//////// next scene osc function callback ///////

OSCFunc({|msg|
  Tdef(\main).pause;
  Tdef(\main).source = {
    inf.do{|i|
      if( i<1 ,{
        Tdef(~scenes[msg[1]]).embed;
      },{
        Tdef(~scenes[i%~scenes.size]).embed;
      });
      ~currentScene = ~scenes[i%~scenes.size];
      ~refreshConsole.value(Tdef(\main).isPlaying, ~currentScene);
    }
  };
  Tdef(\main).resume;
  },

  '/next_scene',
  nil
);

)

// NetAddr("localhost",57120).sendMsg("/next_scene", 0);

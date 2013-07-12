(
var scenes  = [
	"/dev-app/korogaru-pavilion/app/scenes/testScene.sc",
	"/dev-app/korogaru-pavilion/app/scenes/testScene.sc"
];

var synthdefs  = [
	"/dev-app/korogaru-pavilion/app/synths/testSynths.sc",
	"/dev-app/korogaru-pavilion/app/synths/testSynths.sc"
];

synthdefs.do({|defs| defs.loadPaths()});
scenes.do({|scene| scene.loadPaths()});

~scenes = [\testScene, \testScene2];


//////// main task def runner ////////


Tdef(\main,{
	inf.do{|i|
    ("scene_current : "+i).postln;
		Tdef(~scenes[i%~scenes.size]).embed;
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
    }
  };
  Tdef(\main).resume;
  },

  '/next_scene',
  nil
);

)



// NetAddr("localhost",57120).sendMsg("/next_scene", 1);

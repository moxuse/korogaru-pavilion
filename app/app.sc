/*

app.sc

Supercollider Application For KOROGARU PAVILION TCAM
Wrten by Koichiro Mori @ moxuss.org 2013

*/
(

s.waitForBoot({
	var error = nil;

	~alert = { arg message;
		var wind,lab,but;
		wind = QWindow(\alert,Rect(900,750,400,270));
		lab = QStaticText(wind,Rect(120,10,180,60));
		lab.string = message;
		but = QButton(wind, Rect(120,200,140,30) ).action_({wind.close()}).states_([["ok",Color.black]]);
		wind.front;
	};

	"python /dev-app/korogaru-pavilion/app/osc-dmx/oscdmx.py".unixCmd { |res, pid|
		{
			if ( res == 1 ,{
        if ("ps aax | grep osc-dmx".unixCmdGetStdOut.contains("osc-dmx"),{
          ~alert.value( "Already running osc-dmx process..." );
        },{
          ~alert.value( "Could not open Serialport" );
          error = 1;
        });

			},{
				"stoped python osc listener".postln;
			});
		}.fork(AppClock);

		res.postln;
		pid.postln;
	};

	2.0.wait; //wait for error

	if ( nil == error ,{
		fork{

			"load sceanes..".postln;
			"/dev-app/korogaru-pavilion/app/SceneTree.sc".loadPaths();
      "/dev-app/korogaru-pavilion/app/MainTaskDef.sc".loadPaths();

			1.0.wait;
			"start scens -- ".postln;
			Tdef(\main).play;
		}

	});

})
)

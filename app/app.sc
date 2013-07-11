/*

app.sc

Supercollider Application For KOROGARU PAVILION TCAM
Wrten by Koichiro Mori @ moxuss.org 2013

*/
(
var error = nil;

~alert = { arg message;
	var wind,lab,but;
	wind = QWindow(\alert,Rect(900,750,400,270));
	lab = QStaticText(wind,Rect(120,10,180,60));
	lab.string = message;
	but = QButton(wind, Rect(120,200,140,30) ).action_({wind.close()}).states_([["ok",Color.black]]);
	wind.front;
};



error = "python /dev-app/korogaru-pavilion/app/osc-dmx/oscdmx.py".unixCmd { |res, pid|

	{
		if ( res == 1 ,{
			~alert.value( "Could not open Serialport" );
		},{
			"stoped python osc listener".postln;
		});
	}.fork(AppClock);
	res.postln;
	pid.postln;
};

fork{
	1.0.wait;
	"load sceanes..".postln;
	"/dev-app/korogaru-pavilion/app/SceneTree.sc".loadPaths();

	1.0.wait;
	"start scens -- ".postln;
	Tdef(\main).play;
}
)




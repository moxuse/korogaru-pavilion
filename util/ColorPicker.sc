/*
Color Picker & Color Sceane Generator
2013 written by koichiro mori moxus.org
*/
(
var wind = Window.new(\color_scene_maker, Rect(10,1600,500,200));
var d2 = Slider2D.new(wind);
var txf = QTextField.new(wind,Rect(180,80,250,60)).alpha_(0.8);
var btn = QButton.new(wind,Rect(180 ,0, 200, 60));
var btn2 = QButton.new(wind,Rect(400 ,0, 50, 60));
var vt = Slider(wind, Rect(150, 0, 20, 150)).value_(1);
var nb_from = QNumberBox(wind, Rect(0,160,80,30));
var nb_to = QNumberBox(wind, Rect(120,160,80,30));
var nb_step = QNumberBox(wind, Rect(210,160,30,30));
var color;
var colorString;

nb_from.clipLo_(0);nb_to.clipLo_(0);
nb_from.clipHi_(512);nb_to.clipHi_(512);
nb_step.value_(1);

txf.backColor_(Color.black);
txf.focusGainedAction_({var cop=txf.value; cop.postln; ("echo \"" ++ cop.asString ++ "\"| pbcopy").unixCmd; });
txf.stringColor_(Color.white);
d2.action_({|sl|
	color = Color.hsv(min(0.999, sl.x), min(0.999, 1-sl.y), vt.value, 1);
	d2.background_(Color.hsv(min(0.999, sl.x), min(0.999, 1-sl.y), vt.value, 1));
	wind.refresh;
});
d2.mouseUpAction_({colorString = "Color("++color.red.asString ++ "," + color.green.asString ++ "," + color.blue.asString++")";
	("echo \"" ++colorString++"\"| pbcopy").unixCmd;
	txf.value_(colorString);
});

btn.action_({
	var tx;
	txf.value_(colorString);
	tx = "x = DMXRGBCue.new();\nx.range(" ++ nb_from.value ++ "," ++ nb_to.value ++ "," ++ colorString ++ "," ++ nb_step.value ++ ");";
	 ("echo \"" ++ tx ++ "\"| pbcopy").unixCmd;
});

btn2.action_({
	var tx;
	txf.value_(colorString);
	tx = "x = DMXRGBCue.new();\nx.gradationRange(" ++ nb_from.value ++ "," ++ nb_to.value ++ "," ++ colorString ++ "," ++ colorString ++ "," ++  nb_step.value ++ ");";
	 ("echo \"" ++ tx ++ "\"| pbcopy").unixCmd;
});


wind.front;
)

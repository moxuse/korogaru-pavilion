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

(
Tdef(\main,{
	inf.do{
		Tdef(\testScene).embed;
	}
	})
)

)


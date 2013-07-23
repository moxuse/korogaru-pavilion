/*

SceneTree.sc

Supercollider Application For KOROGARU PAVILION TCAM
Wrten by Koichiro Mori @ moxuss.org 2013

*/

(
var scenes  = [
//  ~appDir++"scenes/testScene.sc",
  ~appDir++"scenes/AtomicScene.sc"
];

var synthdefs  = [
  ~appDir++"synths/MasterVolumeSynth.sc",
  ~appDir++"synths/SimplePlayer.sc"
];

synthdefs.do({|defs| defs.loadPaths()});
scenes.do({|scene| scene.loadPaths()});

~scenesP = [ \atomicScene, \rouletteScene ];
~scenesF = [ \atomicSceneFlex ];

)

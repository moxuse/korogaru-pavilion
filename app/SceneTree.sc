/*

SceneTree.sc

Supercollider Application For KOROGARU PAVILION TCAM
Wrten by Koichiro Mori @ moxuss.org 2013

*/

(
var scenes  = [
  ~appDir++"scenes/testScene.sc",
  ~appDir++"scenes/testScene.sc"
];

var synthdefs  = [
  ~appDir++"synths/MasterVolumeSynth.sc",
  ~appDir++"synths/SimplePlayer.sc"
];

synthdefs.do({|defs| defs.loadPaths()});
scenes.do({|scene| scene.loadPaths()});

~scenes = [\testScene, \testScene2, \testScene];

)

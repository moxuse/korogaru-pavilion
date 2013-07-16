/*

SceneTree.sc

Supercollider Application For KOROGARU PAVILION TCAM
Wrten by Koichiro Mori @ moxuss.org 2013

*/

(
var appDir = "/dev-app/korogaru-pavilion/app/";

var scenes  = [
  appDir++"scenes/testScene.sc",
  appDir++"scenes/testScene.sc"
];

var synthdefs  = [
  appDir++"synths/testSynths.sc",
  appDir++"synths/testSynths.sc"
];

synthdefs.do({|defs| defs.loadPaths()});
scenes.do({|scene| scene.loadPaths()});

~scenes = [\testScene, \testScene2, \testScene2];

)

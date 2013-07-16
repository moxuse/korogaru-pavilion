/*

SceneTree.sc

Supercollider Application For KOROGARU PAVILION TCAM
Wrten by Koichiro Mori @ moxuss.org 2013

*/

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

~scenes = [\testScene, \testScene2, \testScene2];

)

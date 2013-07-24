/*

Yamaguchi_YamaSynth.sc

*/

"Loaded Yamaguchi_Yama Synths".postln;

s.sendMsg(\b_allocRead, 80, ~appDir++"sounds/yama_ine.wav");
s.sendMsg(\b_allocRead, 90, ~appDir++"sounds/yama_gyusha.wav");
s.sendMsg(\b_allocRead, 100, ~appDir++"sounds/yama_inoshishi.wav");
s.sendMsg(\b_allocRead, 110, ~appDir++"sounds/yama_suiden.wav");
s.sendMsg(\b_allocRead, 120, ~appDir++"sounds/yama_suzu.wav");


////////////////


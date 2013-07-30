/*

startup.scd

SuperCollider Application For KOROGARU PAVILION YCAM
Wrten by Koichiro Mori @ moxuss.org 2013

*/

//s.options.device = nil;
//s.options.sampleRate_(44100);

"/dev-app/korogaru-pavilion/app/app.sc".loadPaths();

s.options.device = "MOTU 896HD";
s.options.numOutputBusChannels_(22);
2.wait;
s.reboot
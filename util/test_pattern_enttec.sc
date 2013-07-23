/*

lighting test

*/

(
"python /dev-app/korogaru-pavilion/app/osc-dmx/oscdmx.py -p 5000 -d /dev/tty.usbserial-EN119503	".unixCmd;
"python /dev-app/korogaru-pavilion/app/osc-dmx/oscdmx.py -p 5001 -d /dev/tty.usbserial-EN119315	".unixCmd;

~dmx1 = DMX.new();
~dmx2 = DMX.new();

~dmxCue1 = DMXCue.new();
~dmxCue2 = DMXCue.new();

~dmxRGBCue1 = DMXRGBCue.new();
~dmxRGBCue2 = DMXRGBCue.new();

~dmxRGBCue1.range(KPPole.head, KPPole.tail, Color(1,1,1));
~dmxCue1.merge(~dmxRGBCue1);

~dmxRGBCue2.range(KPFlex.head, KPFlex.tail, Color(1,1,1));
~dmxCue2.merge(~dmxRGBCue1);

NetAddr("localhost",5000).sendMsg("/dmx", ~dmxCue1.asRawInt8);
NetAddr("localhost",5001).sendMsg("/dmx", ~dmxCue2.asRawInt8);
)

//////////////////////////

//Fresco

// red
(
~dmxRGBCue1.range(KPPole.head, KPPole.tail, Color(1,0,0));
~dmxCue1.merge(~dmxRGBCue1);
NetAddr("localhost",5000).sendMsg("/dmx", ~dmxCue1.asRawInt8);
)


// green
(
~dmxRGBCue1.range(KPPole.head, KPPole.tail, Color(0,1,0));
~dmxCue1.merge(~dmxRGBCue1);
NetAddr("localhost",5000).sendMsg("/dmx", ~dmxCue1.asRawInt8);
)


// blue
(
~dmxRGBCue1.range(KPPole.head, KPPole.tail, Color(0,0,1));
~dmxCue1.merge(~dmxRGBCue1);
NetAddr("localhost",5000).sendMsg("/dmx", ~dmxCue1.asRawInt8);
)

/////////////////////////////

//Flex

// red
(
~dmxRGBCue2.range(KPFlex.head, KPFlex.tail, Color(1,0,0));
~dmxCue2.merge(~dmxRGBCue2);
NetAddr("localhost",5001).sendMsg("/dmx", ~dmxCue2.asRawInt8);
)


// green
(
~dmxRGBCue2.range(KPFlex.head, KPFlex.tail, Color(0,1,0));
~dmxCue2.merge(~dmxRGBCue2);
NetAddr("localhost",5001).sendMsg("/dmx", ~dmxCue2.asRawInt8);
)


// blue
(
~dmxRGBCue1.range(KPFlex.head, KPFlex.tail, Color(0,0,1));
~dmxCue1.merge(~dmxRGBCue1);
NetAddr("localhost",5001).sendMsg("/dmx", ~dmxCue1.asRawInt8);
)

/////////////////////////////////


//Fresco Step check


/////////////////////////////////


(
Tdef(\stepCheck, {
  KPPole.heads.size.do{|i|
    ~dmxRGBCue1.range(KPPole.head,KPPole.tail,Color(0,0,0));
    ~dmxRGBCue1.range(KPPole.heads[i], KPPole.tails[i], Color(1,1,1));
    ~dmxCue1.merge(~dmxRGBCue1);
    NetAddr("localhost",5000).sendMsg("/dmx", ~dmxCue2.asRawInt8);
   ("current pole: "++ i.asString).postln;
   1.0.wait;
  }
}).play
)

Tdef(\stepCheck).stop();


(
Tdef(\stepCheck, {
  KPPole.heads.size.do{|i|
    4.do{|j|
      ~dmxRGBCue1.range(KPPole.head,KPPole.tail,Color(0,0,0));
      ~dmxRGBCue1.range(KPPole.heads[i] + (j * 3) , (KPPole.heads[i] + (j * 3)) + 2 , Color(1,1,1));
      ~dmxCue1.merge(~dmxRGBCue1);
      NetAddr("localhost",5000).sendMsg("/dmx", ~dmxCue2.asRawInt8);
      ("current pole: "++j.asString ++ " : " ++ i.asString ++ " : " ++ (KPPole.heads[i] + (j*3))).postln;
      0.5.wait;
    }
  }
}).play
)

Tdef(\stepCheck).stop();



//////////////////////////

//kill
(
"killall osc-dmx5000".unixCmd;
"killall osc-dmx5001".unixCmd;


"ps ax | grep  osc".unixCmd;
)
/////////////////////////

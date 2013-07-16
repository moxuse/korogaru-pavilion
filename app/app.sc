/*

app.sc

Supercollider Application For KOROGARU PAVILION TCAM
Wrten by Koichiro Mori @ moxuss.org 2013

*/
(
var wind,lab,volumeLabel,but,volkonob,scenelab,statuslab,stopSeqBut;

~appDir = "/dev-app/korogaru-pavilion/app/";
~oscProcesses = [];
//////main console

~mianConsole = QWindow(\main_console,Rect(900,750,400,380));
scenelab = QStaticText(~mianConsole,Rect(60,10,280,60)).font_(Font("Monaco", 23));
volumeLabel = QStaticText(~mianConsole,Rect(120,160,350,60)).string_("main_vollume: 1");
statuslab = QStaticText(~mianConsole,Rect(60,80,350,60)).string_("status: unknown");
volkonob = QKnob(~mianConsole, Rect(60,160,50,50));
volkonob.action_({|knob| volumeLabel.string_("main_vollume : "++knob.value.round(1e-2)); s.sendMsg("/n_set", 6000, \amp, knob.value);});
volkonob.value_(1.0);
scenelab.string = "current scene: "++"unknown";

but = QButton(~mianConsole, Rect(60,300,180,30) ).action_({
  ~oscProcesses.do({|item| ("killall osc-dmx"++item).unixCmd; });
}).states_([["Close DMX-OSC Port",Color.red]]);

stopSeqBut = QButton(~mianConsole, Rect(60,260,160,30) ).action_({|but|
  switch ( but.value,
    0, {
      Tdef(\main).resume;
    },
    1, {
      Tdef(\main).pause;
  });
  but.value.postln;
}).states_([["Stop Sequence",Color.blue],["Start Sequence",Color.gray]]);

~mianConsole.onClose_({
  ~oscProcesses.do({|item| ("killall osc-dmx"++item).unixCmd; });
  Tdef(\main).stop;
  s.quit();
});

~mianConsole.front;

~refreshConsole = {|isPlaying_, currentScene_|
  defer {
    scenelab.string = "current scene: "++currentScene_;
    if (isPlaying_, {
      statuslab.string_("status: RUNNING").stringColor_(Color.blue);
      }, {
        statuslab.string_("status: STOP").stringColor_(Color.red);
    });
  }
};

//////////////////


//////////// alert

~alert = { arg message;
  var wind,lab,but;
  wind = QWindow(\alert,Rect(900,750,400,270));
  lab = QStaticText(wind,Rect(120,10,180,60));
  lab.string = message;
  but = QButton(wind, Rect(120,200,140,30) ).action_({wind.close()}).states_([["ok",Color.black]]);
  wind.front;
};

//////////////////
s.makeGui;

s.waitForBoot({
  var error = nil;

  "python /dev-app/korogaru-pavilion/app/osc-dmx/oscdmx.py -p 5000 -d /dev/tty.usbserial-EN119503".unixCmd { |res, pid|
    {
      if ( res == 1 ,{
        if ("ps aax | grep osc".unixCmdGetStdOut.contains("osc-dmx"),{
          ~alert.value( "Already running osc-dmx process..." );
          },{
            ~alert.value( "Could not open Serialport" );
            error = 1;
        });

        },{
          "stoped python osc listener".postln;
      });
    }.fork(AppClock);

    res.postln;
    pid.postln;
  };

  2.0.wait; //wait for error

  if ("ps aax | grep osc".unixCmdGetStdOut.contains("osc-dmx"),{
    ~oscProcesses.add("5000");
  });

  if ( nil == error ,{
    fork{

      "load sceanes..".postln;
      "/dev-app/korogaru-pavilion/app/SceneTree.sc".loadPaths();
      "/dev-app/korogaru-pavilion/app/MainTaskDef.sc".loadPaths();

      1.0.wait;
      "start scens -- ".postln;
      Tdef(\main).play;
    }

  });

})
)

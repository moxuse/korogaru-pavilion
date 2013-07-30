/*

Yamaguchi-YamaScene.sc

SuperCollider Application For KOROGARU PAVILION YCAM
Wrten by Koichiro Mori @ moxuss.org 2013

*/

"this is Yamaguchi-Yama Scene".postln;


Tdef(\yamaguchi_YamaSceneFlex,{
  (Date.getDate.asString +"playing Yamaguchi-Yama Scene").postln;

  s.sendMsg(9,"simplePlayerAsrNonRand", 2010,0,1, \bufnum, 80, \out, 7, \gate,1, \amp, 1.4 );
  s.sendMsg(9,"simplePlayerAsrNonRand", 2011,0,1, \bufnum, 90, \out, 8, \gate,1, \amp, 0.6 );
  s.sendMsg(9,"simplePlayerAsrNonRand", 2012,0,1, \bufnum, 100, \out, 9, \gate,1, \amp, 1.0 );
  s.sendMsg(9,"simplePlayerAsrNonRand", 2013,0,1, \bufnum, 110, \out, 10, \gate,1, \amp, 1.4 );
  s.sendMsg(9,"simplePlayerAsrNonRand", 2014,0,1, \bufnum, 120, \out, 11, \gate,1, \amp, 1.0 );

  10.do{|i|
    Tdef(\fadeYama).embed;
    Tdef(\fadeYamaRand).embed;

    if(i >= 9,{
      "free nami synthes".postln;
      s.sendMsg("n_set",2010, \gate, 0);
      s.sendMsg("n_set",2011, \gate, 0);
      s.sendMsg("n_set",2012, \gate, 0);
      s.sendMsg("n_set",2013, \gate, 0);
      s.sendMsg("n_set",2014, \gate, 0);
			"copleted".postln;
    });
  };

});


Tdef(\fadeYama, {
  var timeSpan = 10;

 1.do{
    var newCue;
    var newColorCue;

    newCue = DMXRGBCue.new();
    newCue.range(KPFlex.head,KPFlex.tail,Color(1.0, 0.8, 0.0),1);

    ~mainCueF.merge(newCue);
    ~mainDMXF.currentCue_(~mainCueF);

    newColorCue = DMXRGBCue.new();
    newColorCue.range(KPFlex.head,KPFlex.tail,Color(0.0, 1.0, 0.0),1);

    ~mainDMXF.fadeOSC(~netAddrF, newColorCue, timeSpan, 9);

    (timeSpan+0.2).wait;
  };

  0.25.wait;
});


Tdef(\fadeYamaRand, {
  var timeSpan = 3;
  6.do{
    var newCue;
    var newColorCue;

    KPFlex.rgbSize.do{|i|
      newCue = DMXRGBCue.new();
      newCue.range(i*3, i*3+3, [Color(0.0, 1.0, 0.4.rand), Color(0.2, 1, 0.5.rand)].choose, 1);
      ~mainCueF.merge(newCue);
    };
    ~mainDMXF.currentCue_(~mainCueF);

    KPFlex.rgbSize.do{|i|
      newColorCue = DMXRGBCue.new();
      newColorCue.range(i*3, i*3+3, Color(0, 1.0.rand, 0), 1);
    };

    ~mainDMXF.fadeOSC(~netAddrF, newColorCue, timeSpan, 4);

    (timeSpan+0.2).wait;
  };

  0.75.wait;
});

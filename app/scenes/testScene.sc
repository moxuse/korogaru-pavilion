"this is testSCene".postln;


Tdef(\testScene_sub,{
	"testScene__sub__dayo".postln;
	1.0.wait;
});

Tdef(\testScene,{
	1.do{
		Tdef(\testScene_sub).embed;
		Tdef(\testScene_sub).embed
	}
})
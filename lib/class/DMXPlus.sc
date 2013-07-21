/*
DMXPlus
2013 written by koichiro mori moxus.org
*/

// Additional Methods & Class for DMX.sc


DMXRGBCue : DMXSubCue {
	classvar rgbOffset;

	*new {
		rgbOffset = 3;
		^super.new;
	}

	put { arg id, color;
		var arr = color.asArray;
		arr.do({arg item, i;
			data.put( id + i, item );
		})
	}

	at { arg id;
		var col = Color.new;
		var arr = [data.at(id), data.at(id+1), data.at(id+2)];
		col = Color.fromArray(arr);
		^col;
	}

	range { arg from, to, color, step = 1;
		var arr = (from, from + ( step * rgbOffset )..to);
		arr.do({|item| data.put(item,color.red)});
		arr.do({|item| data.put(item+1,color.green)});
		arr.do({|item| data.put(item+2,color.blue)});
	}

  dvidedData { arg fromData_, toData_, dist_, index_;
    var divVal;

    if(fromData_ == toData_,{
      ^fromData_;
    });

    if ( fromData_ > toData_, {
      divVal = ( (fromData_ - toData_) / dist_ ) * index_ ;
    },{
      divVal = ( (toData_ - fromData_) / dist_ ) * index_ ;
    });

    ^divVal;
  }

  gradationRange { arg from, to, fromColor, toColor, step = 1;
    var arr = (from, from + ( step * rgbOffset )..to);
    var dist = (to - from)/rgbOffset;
    arr.do({|item,i|
      var putData;
      putData = this.dvidedData( fromColor.red, toColor.red, dist, i );
      data.put(item, putData);
      i.postln;
    });

    arr.do({|item,i|
      var putData;
      putData = this.dvidedData( fromColor.green, toColor.green, dist, i );
      data.put(item+1, putData);
      i.postln;
    });

    arr.do({|item,i|
      var putData;
      putData = this.dvidedData( fromColor.blue, toColor.blue, dist, i );
      data.put(item+2, putData);
      i.postln;
    });

  }
}

+ DMXCue {

	asRawInt8 {
		var arr = Int8Array.newFrom(spec.map( this.data ).asInteger);
		^arr;
	}
}

+ DMXSubCue {

	range { arg from, to, val, step=1;
		var arr = (from, from+step..to);
		arr.do({|item| data.put(item,val)});
	}

	clear {
		data.clear;
	}
}

+ DMX {

	sendCue { arg cue_;
		this.currentCue = cue_;
		this.setCue;
	}

  blackoutOSC { |netAddr,time,curve|
		if ( time.isNil, {
			this.fadeOSC( netAddr, DMXCue.new, 1.0, curve );
		},{
			this.fadeOSC( netAddr, DMXCue.new, time, curve );
		});
	}

	fadeOSC { arg netAddr, to, time=1.0, curve=\linear, timestep=0.025;
		var spec, startCue, endCue, nsteps, ddmx, curdmx, tdefname;
    tdefname = ("dmxfade_"++this.hash.asString).asSymbol;
    //tdefname.postln;
		spec = [0,1,curve].asSpec;
		startCue = currentCue;
		if ( to.isKindOf( DMXSubCue ), {
			endCue = currentCue.deepCopy.merge( to );
		}, {
			endCue = to;
		});
		//		endCue.data.postln;
		nsteps = round(time/timestep);
		// can't do more than 256 steps, due to 8bit resolution
		if ( nsteps > 256, { nsteps = 256; timestep = time/nsteps; } );
		ddmx = 1/nsteps;
		//		Tdef( \dmxfade ).envir = ();
		Tdef( tdefname, {
			//	envir = ();
			//			envir.put( \timestep, timestep );
			//			envir.put( \nsteps, nsteps );
			Tdef(tdefname).set( \speed, 1 );
			nsteps.do{ |i|
				currentCue = DMXCue.new;
				//	spec.map( 1-(ddmx*(i+1)) ).postln;
				currentCue.data = (startCue.data * spec.map( 1-(ddmx*(i+1)) ) ) + (endCue.data * spec.map( ddmx*(i+1) ) );
				fadeval = ddmx*(i+1); // could be displayed
				Tdef(tdefname).set( \fadeval, fadeval );
					//currentCue.data.postln;

				netAddr.sendMsg("/dmx" , currentCue.asRawInt8 );
				//("send ....." + netAddr + currentCue.at(0) ).postln;
				( timestep / Tdef(tdefname).envir.speed ).wait;
				//( timestep ).wait;
			};
		});
		Tdef( tdefname ).play(SystemClock);
	}
}
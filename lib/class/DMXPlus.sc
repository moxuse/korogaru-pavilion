/*
DMXPlus
2013 written by koichiro mori moxus.org
*/

// Additional Methods & Class for DMX.sc

DMXRGBCue : DMXSubCue {
	classvar rgbOffset;

	*new{
		rgbOffset = 3;
		^super.new;
	}

	put{ arg id, color;
		var arr = color.asArray;
		arr.do({arg item, i;
			data.put( id + i, item );
		})
	}

	at{ arg id;
		var col = Color.new;
		var arr = [data.at(id), data.at(id+1), data.at(id+2)];
		col = Color.fromArray(arr);
		^col;
	}

	range{ arg from, to, color, step = 1;
		var arr = (from, from + ( step * rgbOffset )..to);
		arr.do({|item| data.put(item,color.red)});
		arr.do({|item| data.put(item+1,color.green)});
		arr.do({|item| data.put(item+2,color.blue)});
	}
}

+ DMXSubCue{

	range{arg from, to, val, step=1;
		var arr = (from, from+step..to);
		arr.do({|item| data.put(item,val)});
	}

	clear{
		data.clear;
	}
}

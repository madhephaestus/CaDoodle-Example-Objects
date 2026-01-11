package openscad;

import com.neuronrobotics.bowlerstudio.vitamins.Vitamins;
import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.parametrics.*

import com.neuronrobotics.bowlerstudio.scripting.ScriptingEngine

import eu.mihosoft.vrl.v3d.CSG

HashMap<String,Double> params=new HashMap<String, Double>();
CSG getObject(){
	if(args==null) {
		args=["Test_key_here"]
	}
	ArrayList<Double> options = new  ArrayList<Double> ()
	options.addAll(Arrays.asList(23,30))
	LengthParameter type = new LengthParameter(csgdb,	args[0]+"_CaDoodle_gggears_Type","spurgear",Arrays.asList("spurgear"))
	LengthParameter pitch = new LengthParameter(csgdb,	args[0]+"_CaDoodle_gggears_Pitch",23,options)
	
	HashMap<String,Object> params=new HashMap<String, Object>();
	params.put("type", type.toString())
	params.put("--number-of-teeth", pitch.toString())
	CSG bin=	Build123dLoader.toCSG(csgdb, params)
	
	bin.setNoScale(true)
	return bin
	.setParameter(csgdb,type)
	.setParameter(csgdb,pitch)
	.setRegenerate({getObject()})
}

return getObject()

 
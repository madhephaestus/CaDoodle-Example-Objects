package openscad;

import com.neuronrobotics.bowlerstudio.vitamins.Vitamins;
import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.parametrics.*
import eu.mihosoft.vrl.v3d.parametrics.CSGDatabase;
import com.neuronrobotics.bowlerstudio.scripting.ScriptingEngine

import eu.mihosoft.vrl.v3d.CSG

HashMap<String,Double> params=new HashMap<String, Double>();
CSG getObject(){
	if(args==null) {
		args=["Test_key_here"]
	}
	ArrayList<Double> options = new  ArrayList<Double> ()
	options.addAll(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10))
	LengthParameter x = new LengthParameter(	args[0]+"_CaDoodle_gridfinity_X",1,options)
	LengthParameter y = new LengthParameter(	args[0]+"_CaDoodle_gridfinity_Y",1,options)
	LengthParameter mag = new LengthParameter(	args[0]+"_CaDoodle_gridfinity_Magnets",0,[0,1])
	LengthParameter style_plate = new LengthParameter(	args[0]+"_CaDoodle_gridfinity_Style",0,[0,1,2,3,4])
	
	if(style_plate.getMM()<1)
		style_plate.setMM(1);
	HashMap<String,Object> params=new HashMap<String, Object>();
	params.put("gridx", x.getMM())
	params.put("gridy", y.getMM())
	params.put("enable_magnet", mag.getMM()>0)
	params.put("style_plate", (int)style_plate.getMM())
	
	CSG bin=	ScriptingEngine.gitScriptRun(
			"https://github.com/kennetek/gridfinity-rebuilt-openscad.git",
			"gridfinity-rebuilt-baseplate.scad",[params])
	
	CSGDatabase.saveDatabase()
	return bin
	.setParameter(mag)
	.setParameter(y)
	.setParameter(x)
	.setParameter(style_plate)
	.setRegenerate({getObject()})
}
return getObject()

 
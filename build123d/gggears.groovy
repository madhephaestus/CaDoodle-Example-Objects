package build123d;

import com.neuronrobotics.bowlerstudio.vitamins.Vitamins;
import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.parametrics.*

import com.neuronrobotics.bowlerstudio.scripting.ScriptingEngine

import eu.mihosoft.vrl.v3d.CSG

HashMap<String,Double> params=new HashMap<String, Double>();
List<CSG> getObject(){
	if(args==null) {
		args=["Test_key_here"]
	}
	ArrayList<Double> options = new  ArrayList<Double> ()
	options.addAll(Arrays.asList(23,30))
	StringParameter type = new StringParameter(csgdb,
			args[0]+"_CaDoodle_gggears_Type","spurgear",
			new ArrayList<String>(Arrays.asList("spurgear")))
	LengthParameter pitch = new LengthParameter(csgdb,
			args[0]+"_CaDoodle_gggears_Pitch",
			23,
			options)

	ArrayList<Object> params=new ArrayList< Object>();
	params.add("gggears")
	params.add( type.getStrValue())
	params.add("--number-of-teeth")
	params.add(pitch.getMM())
	List<CSG> all=	Build123dLoader.toCSG(csgdb, params)

	for(CSG bin:all) {
		bin.setNoScale(true)
		bin
				.setParameter(csgdb,type)
				.setParameter(csgdb,pitch)
				.setRegenerate({getObject()})
	}
	return all
}

return getObject()


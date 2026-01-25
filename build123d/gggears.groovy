package build123d;

import com.neuronrobotics.bowlerstudio.vitamins.Vitamins;
import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.parametrics.*

import com.neuronrobotics.bowlerstudio.scripting.Build123dLoader
import com.neuronrobotics.bowlerstudio.scripting.ScriptingEngine

import eu.mihosoft.vrl.v3d.CSG

HashMap<String,Double> params=new HashMap<String, Double>();
List<CSG> getObject(){
	if(args==null) {
		args=["Test_key_here"]
	}
	ArrayList<Double> options = new  ArrayList<Double> ()
	for(int i=10;i<=100;i++) {
		options.add(i);
	}
	StringParameter type = new StringParameter(csgdb,
			args[0]+"_CaDoodle_py_gearwork_Type","SpurGear",
			new ArrayList<String>(Arrays.asList("HelicalGear","SpurGear")))
	LengthParameter numTeeth = new LengthParameter(csgdb,
			args[0]+"_CaDoodle_py_gearworks_Teeth",
			23,
			options)
	LengthParameter height = new LengthParameter(csgdb,
		args[0]+"_CaDoodle_py_gearworks_Height",
		5,
		new  ArrayList<Double> (Arrays.asList(1,5,20)))
	
	ArrayList<Double> modOpts = new  ArrayList<Double> (Arrays.asList(0.5,0.75,0.8,1,1.25,1.5,1.75,2,2.5,2.75,3,3.25,3.5,3.75,4,4.5,5,5.5,6,7,8))
	LengthParameter module = new LengthParameter(csgdb,
		args[0]+"_CaDoodle_py_gearworks_Module",
		1,
		modOpts)


	ArrayList<Object> params=new ArrayList< Object>();
	params.add("py_gearworks")
	params.add( type.getStrValue())
	params.add("--number-of-teeth")
	params.add(numTeeth.getMM())
	params.add("--height")
	params.add(height.getMM())
	params.add("--module")
	params.add(module.getMM())
	List<CSG> all=	Build123dLoader.toCSG(csgdb, params)

	for(CSG bin:all) {
		bin.setNoScale(true)
		bin.setParameter(csgdb,type)
			.setParameter(csgdb,numTeeth)
			.setParameter(csgdb,height)
			.setParameter(csgdb,module)
			.setRegenerate({getObject()})
	}
	return all

}

return getObject()


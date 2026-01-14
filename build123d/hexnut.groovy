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


	ArrayList<Object> params=new ArrayList< Object>();
	params.add("bd_warehouse")
	params.add("hexnut")
	List<CSG> all=	Build123dLoader.toCSG(csgdb, params)

	for(CSG bin:all) {
		bin.setNoScale(true)
		bin.setRegenerate({getObject()})
	}
	return all

}

return getObject()


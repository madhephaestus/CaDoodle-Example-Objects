import com.neuronrobotics.bowlerstudio.scripting.cadoodle.CaDoodleFile
import com.neuronrobotics.bowlerstudio.vitamins.Vitamins; 
import com.neuronrobotics.sdk.addons.kinematics.VitaminLocation
import com.neuronrobotics.sdk.addons.kinematics.math.TransformNR

import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.parametrics.StringParameter
import eu.mihosoft.vrl.v3d.parametrics.CSGDatabase;
CSG getObject(){
	if(args==null)
		args=["Test_key_here"]
	StringParameter size = new StringParameter(	"capScrew"+" Default","M8",Vitamins.listVitaminSizes("capScrew"))	
	if(size.getStrValue().length()==0)
		size.setStrValue("M8")
	StringParameter word = new StringParameter(	args[0]+"_CaDoodle_TextGeneration_Size",size.getStrValue(),Vitamins.listVitaminSizes("capScrew"))
	size.setStrValue(word.getStrValue())
	println "Loading the cap screw model! "+args
	if(args.size()>1)if(!args[1].get("PreventBomAdd"))
		CaDoodleFile.getBoM().addVitamin(new VitaminLocation(false,	args[0], "capScrew", word.getStrValue(), new TransformNR()),true)
	def part= Vitamins.get("capScrew",word.getStrValue()).setIsHole(true)
	CSGDatabase.saveDatabase() 
	return part.setParameter(word).setRegenerate({getObject()})
}
return getObject()

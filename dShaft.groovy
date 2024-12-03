import com.neuronrobotics.bowlerstudio.vitamins.Vitamins; 
import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.parametrics.StringParameter
import eu.mihosoft.vrl.v3d.parametrics.CSGDatabase;
import com.neuronrobotics.bowlerstudio.scripting.cadoodle.CaDoodleFile;
CSG getObject(){
	if(args==null)
		args=["Test_key_here"]
	StringParameter size = new StringParameter(	"dShaft"+" Default","WPI-gb37y3530-50en",Vitamins.listVitaminSizes("dShaft"))	
	if(size.getStrValue().length()==0)
		size.setStrValue("WPI-gb37y3530-50en")
	StringParameter word = new StringParameter(	args[0]+"_CaDoodle_TextGeneration_Size",size.getStrValue(),Vitamins.listVitaminSizes("dShaft"))
	size.setStrValue(word.getStrValue())
	if(args.size()>1)if(!args[1].get("PreventBomAdd"))
		CaDoodleFile.getBoM().addVitamin(new VitaminLocation(false,	args[0], "dShaft" , word.getStrValue(), new TransformNR()),true) 
	def part= Vitamins.get("dShaft",word.getStrValue()).setIsHole(true)
	CSGDatabase.saveDatabase() 
	return part.setParameter(word).setRegenerate({getObject()})
}
return getObject()

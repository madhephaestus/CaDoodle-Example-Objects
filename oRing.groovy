import com.neuronrobotics.bowlerstudio.vitamins.Vitamins; 
import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.parametrics.StringParameter
CSG getObject(){
	if(args==null)
		args=["Test_key_here"]
	StringParameter size = new StringParameter(	"oRing"+" Default","",Vitamins.listVitaminSizes("oRing"))	
	StringParameter word = new StringParameter(	args[0]+"_CaDoodle_TextGeneration_Size",size.getStrValue(),Vitamins.listVitaminSizes("oRing"))
	size.setStrValue(word.getStrValue())
	def part= Vitamins.get("oRing",word.getStrValue()).setIsHole(true)
	CSGDatabase.saveDatabase() 
	return part.setParameter(word).setRegenerate({getObject()})
}
return getObject()

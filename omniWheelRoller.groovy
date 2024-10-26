import com.neuronrobotics.bowlerstudio.vitamins.Vitamins; 
import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.parametrics.StringParameter
import eu.mihosoft.vrl.v3d.parametrics.CSGDatabase;
CSG getObject(){
	if(args==null)
		args=["Test_key_here"]
	StringParameter size = new StringParameter(	"omniWheelRoller"+" Default","",Vitamins.listVitaminSizes("omniWheelRoller"))	
	StringParameter word = new StringParameter(	args[0]+"_CaDoodle_TextGeneration_Size",size.getStrValue(),Vitamins.listVitaminSizes("omniWheelRoller"))
	size.setStrValue(word.getStrValue())
	def part= Vitamins.get("omniWheelRoller",word.getStrValue()).setIsHole(true)
	CSGDatabase.saveDatabase() 
	return part.setParameter(word).setRegenerate({getObject()})
}
return getObject()

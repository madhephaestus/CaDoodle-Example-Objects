import com.neuronrobotics.bowlerstudio.vitamins.Vitamins; 
import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.parametrics.StringParameter
import eu.mihosoft.vrl.v3d.parametrics.CSGDatabase;
CSG getObject(){
	if(args==null)
		args=["Test_key_here"]
	StringParameter size = new StringParameter(	"hobbyServoHorn"+" Default","tproSG90_1",Vitamins.listVitaminSizes("hobbyServoHorn"))	
	if(size.getStrValue().length()==0)
		size.setStrValue("tproSG90_1")
	StringParameter word = new StringParameter(	args[0]+"_CaDoodle_TextGeneration_Size",size.getStrValue(),Vitamins.listVitaminSizes("hobbyServoHorn"))
	size.setStrValue(word.getStrValue())
	def part= Vitamins.get("hobbyServoHorn",word.getStrValue()).setIsHole(true)
	CSGDatabase.saveDatabase() 
	return part.setParameter(word).setRegenerate({getObject()})
}
return getObject()

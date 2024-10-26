import com.neuronrobotics.bowlerstudio.vitamins.Vitamins; 
import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.parametrics.StringParameter
import eu.mihosoft.vrl.v3d.parametrics.CSGDatabase;
CSG getObject(){
	if(args==null)
		args=["Test_key_here"]
	StringParameter size = new StringParameter(	"vex_Hex_Nut_Retainer"+" Default","",Vitamins.listVitaminSizes("vex_Hex_Nut_Retainer"))	
	StringParameter word = new StringParameter(	args[0]+"_CaDoodle_TextGeneration_Size",size.getStrValue(),Vitamins.listVitaminSizes("vex_Hex_Nut_Retainer"))
	size.setStrValue(word.getStrValue())
	def part= Vitamins.get("vex_Hex_Nut_Retainer",word.getStrValue()).setIsHole(true)
	CSGDatabase.saveDatabase() 
	return part.setParameter(word).setRegenerate({getObject()})
}
return getObject()

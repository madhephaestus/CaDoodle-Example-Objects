import com.neuronrobotics.bowlerstudio.vitamins.Vitamins; 
import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.parametrics.StringParameter
CSG getObject(){
	if(args==null)
		args=["Test_key_here"]
		StringParameter word = new StringParameter(	args[0]+"_CaDoodle_TextGeneration_Size","Flat_Bearing",["1_Post_Hex_Nut_Retainer","1_Post_Hex_Nut_Retainer_With_Bearing","4_Post_Hex_Nut_Retainer","Flat_Bearing"])
	def part= Vitamins.get("vex_Hex_Nut_Retainer",word.getStrValue()).setIsHole(true)
	return part.setParameter(word).setRegenerate({getObject()})
}
return getObject()

import com.neuronrobotics.bowlerstudio.vitamins.Vitamins; 
import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.parametrics.StringParameter
CSG getObject(){
	if(args==null)
		args=["Test_key_here"]
		StringParameter word = new StringParameter(	args[0]+"_CaDoodle_TextGeneration_Size","v5_11w",["269","393","v5_11w"])
	def part= Vitamins.get("vexMotor",word.getStrValue()).setIsHole(true)
	return part.setParameter(word).setRegenerate({getObject()})
}
return getObject()

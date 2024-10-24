import com.neuronrobotics.bowlerstudio.vitamins.Vitamins; 
import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.parametrics.StringParameter
CSG getObject(){
	if(args==null)
		args=["Test_key_here"]
		StringParameter word = new StringParameter(	args[0]+"_CaDoodle_TextGeneration_Size","HS84T",["12T","15T","36T","60T","84T","HS12T","HS36T","HS60T","HS84T"])
	def part= Vitamins.get("vexGear",word.getStrValue()).setIsHole(true)
	return part.setParameter(word).setRegenerate({getObject()})
}
return getObject()

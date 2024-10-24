import com.neuronrobotics.bowlerstudio.vitamins.Vitamins; 
import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.parametrics.StringParameter
CSG getObject(){
	if(args==null)
		args=["Test_key_here"]
		StringParameter word = new StringParameter(	args[0]+"_CaDoodle_TextGeneration_Size","6 inch",["0.25 inch","0.5 inch","0.75 inch","1 inch","1.5 inch","2 inch","2.5 inch","3 inch","4 inch","5 inch","6 inch"])
	def part= Vitamins.get("vexStandoff",word.getStrValue()).setIsHole(true)
	return part.setParameter(word).setRegenerate({getObject()})
}
return getObject()

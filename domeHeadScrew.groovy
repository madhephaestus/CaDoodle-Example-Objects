import com.neuronrobotics.bowlerstudio.vitamins.Vitamins; 
import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.parametrics.StringParameter
CSG getObject(){
	if(args==null)
		args=["Test_key_here"]
		StringParameter word = new StringParameter(	args[0]+"_CaDoodle_TextGeneration_Size","Imp8",Vitamins.listVitaminSizes("domeHeadScrew"))
	def part= Vitamins.get("domeHeadScrew",word.getStrValue()).setIsHole(true)
	return part.setParameter(word).setRegenerate({getObject()})
}
return getObject()

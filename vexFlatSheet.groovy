import com.neuronrobotics.bowlerstudio.vitamins.Vitamins; 
import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.parametrics.StringParameter
CSG getObject(){
	if(args==null)
		args=["Test_key_here"]
		StringParameter word = new StringParameter(	args[0]+"_CaDoodle_TextGeneration_Size","Steel 5x5",["Aluminum 1x10","Aluminum 1x15","Aluminum 1x5","Aluminum 5x10","Aluminum 5x15","Aluminum 5x5","Steel 10x10","Steel 1x10","Steel 1x15","Steel 1x5","Steel 5x10","Steel 5x15","Steel 5x5"])
	def part= Vitamins.get("vexFlatSheet",word.getStrValue()).setIsHole(true)
	return part.setParameter(word).setRegenerate({getObject()})
}
return getObject()

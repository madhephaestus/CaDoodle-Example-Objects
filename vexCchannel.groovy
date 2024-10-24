import com.neuronrobotics.bowlerstudio.vitamins.Vitamins; 
import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.parametrics.StringParameter
CSG getObject(){
	if(args==null)
		args=["Test_key_here"]
		StringParameter word = new StringParameter(	args[0]+"_CaDoodle_TextGeneration_Size","Steel 5x5",["Aluminum 2x15","Aluminum 2x35","Aluminum 3x15","Aluminum 3x35","Aluminum 3x5","Aluminum 5x15","Aluminum 5x35","Aluminum 5x5","Aluminum_1x2x10","Aluminum_1x2x25","Steel 2x15","Steel 2x35","Steel 2x5","Steel 3x15","Steel 3x35","Steel 3x5","Steel 5x15","Steel 5x35","Steel 5x5"])
	def part= Vitamins.get("vexCchannel",word.getStrValue()).setIsHole(true)
	return part.setParameter(word).setRegenerate({getObject()})
}
return getObject()

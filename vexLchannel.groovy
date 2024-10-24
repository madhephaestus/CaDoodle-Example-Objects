import com.neuronrobotics.bowlerstudio.vitamins.Vitamins; 
import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.parametrics.StringParameter
CSG getObject(){
	if(args==null)
		args=["Test_key_here"]
		StringParameter word = new StringParameter(	args[0]+"_CaDoodle_TextGeneration_Size","Steel 5",["2x2x25_Aluminum","2x2x35_Aluminum","3x3x25_Aluminum","Aluminum 10","Aluminum 15","Aluminum 35","Aluminum 5","Steel 10","Steel 15","Steel 35","Steel 5"])
	def part= Vitamins.get("vexLchannel",word.getStrValue()).setIsHole(true)
	return part.setParameter(word).setRegenerate({getObject()})
}
return getObject()

import com.neuronrobotics.bowlerstudio.vitamins.Vitamins; 
import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.parametrics.StringParameter
CSG getObject(){
	if(args==null)
		args=["Test_key_here"]
		StringParameter word = new StringParameter(	args[0]+"_CaDoodle_TextGeneration_Size","tproTG90",["DHV56mg_sub_Micro","fs90r","hv5932mg","hv6214mg","mg92b","standard","standardMicro","towerProMG91","tproSG90","tproTG90"])
	def part= Vitamins.get("hobbyServo",word.getStrValue()).setIsHole(true)
	return part.setParameter(word).setRegenerate({getObject()})
}
return getObject()

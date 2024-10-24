import com.neuronrobotics.bowlerstudio.vitamins.Vitamins; 
import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.parametrics.StringParameter
CSG getObject(){
	if(args==null)
		args=["Test_key_here"]
		StringParameter word = new StringParameter(	args[0]+"_CaDoodle_TextGeneration_Size","tproSG90_1",["DHV56mg_sub_Micro_1","DHV56mg_sub_Micro_2","DHV56mg_sub_Micro_4","fs90r_1","hv6214mg_1","hv6214mg_6","standard4","standardMicro1","tproSG90_1"])
	def part= Vitamins.get("hobbyServoHorn",word.getStrValue()).setIsHole(true)
	return part.setParameter(word).setRegenerate({getObject()})
}
return getObject()

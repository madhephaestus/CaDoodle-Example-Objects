import com.neuronrobotics.bowlerstudio.vitamins.Vitamins; 
import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.parametrics.StringParameter
CSG getObject(){
	if(args==null)
		args=["Test_key_here"]
		StringParameter word = new StringParameter(	args[0]+"_CaDoodle_TextGeneration_Size","M8",["6#32","8#32","Inch-1_4-20","M1.6","M10","M12","M14","M16","M18","M2","M2.5","M20","M22","M24","M27","M3","M30","M36","M3x16","M4","M42","M5","M5x100","M5x12","M5x25","M5x45","M5x75","M6","M8"])
	def part= Vitamins.get("capScrew",word.getStrValue()).setIsHole(true)
	return part.setParameter(word).setRegenerate({getObject()})
}
return getObject()

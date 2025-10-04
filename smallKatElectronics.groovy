import com.neuronrobotics.bowlerstudio.vitamins.Vitamins; 
import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.parametrics.StringParameter
CSG getObject(){
	if(args==null)
		args=["Test_key_here"]
		StringParameter word = new StringParameter(csgdb,	args[0]+"_CaDoodle_TextGeneration_Size","motherboard",Vitamins.listVitaminSizes("smallKatElectronics"))
	def part= Vitamins.get("smallKatElectronics",word.getStrValue()).setIsHole(true)
	return part.setParameter(csgdb,word).setRegenerate({getObject()})
}
return getObject()

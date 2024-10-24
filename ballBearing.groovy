import com.neuronrobotics.bowlerstudio.vitamins.Vitamins; 
import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.parametrics.StringParameter
CSG getObject(){
	if(args==null)
		args=["Test_key_here"]
		StringParameter word = new StringParameter(	args[0]+"_CaDoodle_TextGeneration_Size","Thrust_1andAHalfinch",["603zz","604zz","605zz","606zz","607zz","608zz","623zz","624zz","625zz","626zz","627zz","634zz","635zz","673zz","6810_2RS","683zz","684zz","685zz","686zz","687zz","688zz","689zz","6920_2RS","693zz","694zz","695zz","696zz","697zz","698zz","699zz","R8-2RS","R8-60355K505","Thrust_1andAHalfinch"])
	def part= Vitamins.get("ballBearing",word.getStrValue()).setIsHole(true)
	return part.setParameter(word).setRegenerate({getObject()})
}
return getObject()

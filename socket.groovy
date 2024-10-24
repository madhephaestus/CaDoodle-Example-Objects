import com.neuronrobotics.bowlerstudio.vitamins.Vitamins; 
import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.parametrics.StringParameter
CSG getObject(){
	if(args==null)
		args=["Test_key_here"]
		StringParameter word = new StringParameter(	args[0]+"_CaDoodle_TextGeneration_Size","9mm",["10mm","10mm_spline","11_16_in","11mm","11mm_spline","12mm","12mm_spline","13mm","13mm_spline","14mm","14mm_spline","15mm","15mm_spline","16mm","16mm_spline","17mm","17mm_spline","18mm","18mm_spline","19mm","19mm_spline","1_2_in","1_4_in","3_4_in","3_8_in","5_16_in","5_8_in","6mm","7_16_in","7mm","8mm","9_16_in","9_32_in","9mm"])
	def part= Vitamins.get("socket",word.getStrValue()).setIsHole(true)
	return part.setParameter(word).setRegenerate({getObject()})
}
return getObject()

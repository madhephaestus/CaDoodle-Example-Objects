import eu.mihosoft.vrl.v3d.*
import javafx.scene.paint.Color
import eu.mihosoft.vrl.v3d.*
import javafx.scene.paint.Color

import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.Cube
import javafx.scene.paint.Color

import eu.mihosoft.vrl.v3d.parametrics.*;
CSG getObject(){
	if(args==null)
		args=["Test_key_here"]
	ArrayList<Double> options = new  ArrayList<Double> ()
	options.addAll(Arrays.asList(3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,64))
	LengthParameter word = new LengthParameter(	args[0]+"_CaDoodle_ConeSide_Sides",
										4,options)
	int getMM = (int)word.getMM()
	CSG text =  new Cylinder(20/Math.sqrt(2),0,20,getMM).toCSG().toZMin().setColor(Color.YELLOW).rotz(45)
	CSGDatabase.saveDatabase();
	return text
		.setParameter(word)
		.setRegenerate({getObject()})
}
return getObject()

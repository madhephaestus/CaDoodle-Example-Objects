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
	options.addAll(Arrays.asList(3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20))
	LengthParameter word = new LengthParameter(	args[0]+"_CaDoodle_CylinderGeneration_Sides",
										16,options)

	int getMM = (int)word.getMM()
	CSG text =  new Cylinder(10,10,20,getMM).toCSG().toZMin().setColor(Color.ORANGE)
	CSGDatabase.saveDatabase();
	return text
		.setParameter(word)
		.setRegenerate({getObject()})
}
return getObject()

//return new Cylinder(10,20).toCSG().toZMin().setColor(Color.ORANGE)

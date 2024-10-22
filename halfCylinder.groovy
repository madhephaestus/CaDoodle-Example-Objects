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
	options.addAll(Arrays.asList(4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,64))
	LengthParameter word = new LengthParameter(	args[0]+"_CaDoodle_CylinderGeneration_Sides",
										16,options)

	int getMM = (int)word.getMM()
	CSG toCSGToZMin = new Cylinder(10,10,20,getMM).toCSG().toZMin()
	toCSGToZMin=toCSGToZMin.rotx(90).moveToCenterY().intersect(toCSGToZMin.getBoundingBox())
	CSG text =  toCSGToZMin.setColor(Color.LIGHTBLUE)
	CSGDatabase.saveDatabase();
	return text
		.setParameter(word)
		.setRegenerate({getObject()})
}
return getObject()

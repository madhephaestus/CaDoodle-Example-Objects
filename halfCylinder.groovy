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
	options.addAll(Arrays.asList(2,4,6,8,10,12,14,16,18,64))
	LengthParameter word = new LengthParameter(csgdb,	args[0]+"_CaDoodle_CylinderGeneration_Sides",
											Integer.parseInt(com.neuronrobotics.bowlerstudio.assets.ConfigurationDatabase.get("CaDoodle", "DefaultNumberOfSides", "16").toString())
,options)

	int getMM = ((int)word.getMM())*2
	CSG toCSGToZMin = new Cylinder(10,10,20,getMM).toCSG().toZMin()
	toCSGToZMin=toCSGToZMin.rotx(90).moveToCenterY().intersect(toCSGToZMin.getBoundingBox())
	CSG text =  toCSGToZMin.setColor(Color.LIGHTBLUE)
	csgdb.saveDatabase();
	return text
		.setParameter(csgdb,word)
		.setRegenerate({getObject()})
}
return getObject()

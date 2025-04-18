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
	options.addAll(Arrays.asList(3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,64))
	LengthParameter word = new LengthParameter(	args[0]+"_CaDoodle_ShereGeneration_Sides",
										16,options)

	CSG text = new Sphere(10,(int)(word.getMM()),(int)(word.getMM()/2)).toCSG().rotx(90).toZMin().setColor(Color.LIGHTBLUE)
	CSGDatabase.saveDatabase();
	return text
		.setParameter(word)
		.setRegenerate({getObject()})
}
return getObject()

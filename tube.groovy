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
	LengthParameter word = new LengthParameter(	args[0]+"_CaDoodle_TubeSide_Sides",
										16,options)
	LengthParameter top = new LengthParameter(	args[0]+"_CaDoodle_Tube_Wall",
		2.5,[])
	LengthParameter bottom = new LengthParameter(	args[0]+"_CaDoodle_TubeBottom_Rad",
		10,[])
	int getMM = (int)word.getMM()
	double thick = top.getMM();
	CSG toCSG = new Cylinder(bottom.getMM(),bottom.getMM(),10,getMM).toCSG()
	CSG core = new Cylinder(bottom.getMM()-thick,bottom.getMM()-thick,20,getMM).toCSG()
	
	CSG text =  toCSG.difference(core).toZMin().setColor(Color.YELLOWGREEN)
	CSGDatabase.saveDatabase();
	return text
		.setParameter(word)
		.setParameter(top)
		.setParameter(bottom)
		.setRegenerate({getObject()})
}
return getObject()

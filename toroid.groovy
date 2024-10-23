import eu.mihosoft.vrl.v3d.*
import javafx.scene.paint.Color
import eu.mihosoft.vrl.v3d.*
import javafx.scene.paint.Color
import javafx.scene.paint.Color

import eu.mihosoft.vrl.v3d.parametrics.*;
CSG getObject(){
	if(args==null)
		args=["Test_key_here"]
	ArrayList<Double> options = new  ArrayList<Double> ()
	options.addAll(Arrays.asList(3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,64))
	LengthParameter word = new LengthParameter(	args[0]+"_CaDoodle_ToroidSide_Sides",
										16,options)
	LengthParameter top = new LengthParameter(	args[0]+"_CaDoodle_ToroidTop_Inner",
		3,[])
	LengthParameter bottom = new LengthParameter(	args[0]+"_CaDoodle_ToroidBottom_Outer",
		10,[])
	int getMM = (int)word.getMM()
	
	CSG text =  new Toroid(top.getMM(),bottom.getMM(),getMM,getMM).toCSG().rotx(90).toZMin().setColor(Color.BLUE)
	CSGDatabase.saveDatabase();
	return text
		.setParameter(word)
		.setParameter(top)
		.setParameter(bottom)
		.setRegenerate({getObject()})
}
return getObject()

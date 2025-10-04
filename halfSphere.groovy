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
	options.addAll(Arrays.asList(4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,64))
	LengthParameter word = new LengthParameter(csgdb,	args[0]+"_CaDoodle_ShereGeneration_Sides",
											Integer.parseInt(com.neuronrobotics.bowlerstudio.assets.ConfigurationDatabase.get("CaDoodle", "DefaultNumberOfSides", "16").toString())
,options)

	int getMM2 = (int)(word.getMM()/2)
	if(getMM2<2)getMM2=2
	
	CSG toCSG = new Sphere(10,(int)(word.getMM()),getMM2).toCSG().rotx(90)
	toCSG=toCSG.intersect(toCSG.getBoundingBox().toZMin())
	CSG text = toCSG.toZMin().setColor(Color.PINK)
	csgdb.saveDatabase();
	return text
		.setParameter(csgdb,word)
		.setRegenerate({getObject()})
}
return getObject()

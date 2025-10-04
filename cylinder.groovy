import com.neuronrobotics.bowlerstudio.assets.ConfigurationDatabase

import eu.mihosoft.vrl.v3d.*
import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.Cube
import javafx.scene.paint.Color

import eu.mihosoft.vrl.v3d.parametrics.*;
CSG getObject(){
	if(args==null)
		args=["Test_key_here"]
	ArrayList<Double> options = new  ArrayList<Double> ()
	int numberOfSidesInt = Integer.parseInt(ConfigurationDatabase.get("CaDoodle", "DefaultNumberOfSides", "16").toString());
	options.addAll(Arrays.asList(3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,64))
	LengthParameter word = new LengthParameter(csgdb,	args[0]+"_CaDoodle_CylinderGeneration_Sides",
										numberOfSidesInt,options)
	LengthParameter diam = new LengthParameter(csgdb,	args[0]+"_CaDoodle_CylinderGeneration_Diameter",
		20,options)
	LengthParameter height = new LengthParameter(csgdb,	args[0]+"_CaDoodle_CylinderGeneration_Height",
		20,options)
	ArrayList<Object> radOpts = []
	for(int i=0;i<10;i++) {
		radOpts.add(i)
	}
	LengthParameter rad = new LengthParameter(csgdb,	args[0]+"_CaDoodle_CylinderGeneration_Round",
		0,radOpts)
	LengthParameter chamfer = new LengthParameter(csgdb,	args[0]+"_CaDoodle_CylinderGeneration_Chamfer",
		0,radOpts)
	
	int sides = (int)word.getMM()
	CSG local = null
	double radius = diam.getMM()/2
	double h=height.getMM()
	if(rad.getMM()>0) {
		local=new RoundedCylinder(radius,radius,h,sides).cornerRadius(rad.getMM()).toCSG()
		chamfer.setMM(0)
	}else if (chamfer.getMM()>0) {
		local=new ChamferedCylinder(radius,h,chamfer.getMM(),sides).toCSG()
		rad.setMM(0);
	}else {
		local= new Cylinder(radius,radius,h,sides).toCSG()
	}
	CSG text =  local.toZMin().setColor(Color.ORANGE)
	csgdb.saveDatabase();
	return text
		.setParameter(csgdb,word)
		.setParameter(csgdb,rad)
		.setParameter(csgdb,chamfer)
		.setParameter(csgdb,diam)
		.setParameter(csgdb,height)
		.setRegenerate({getObject()})
}
return getObject()

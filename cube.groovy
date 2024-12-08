import eu.mihosoft.vrl.v3d.Cube
import eu.mihosoft.vrl.v3d.*
import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.RoundedCube
import javafx.scene.paint.Color

import eu.mihosoft.vrl.v3d.parametrics.*;
CSG getObject(){
	if(args==null)
		args=["Test_key_here"]
	ArrayList<Double> options = new  ArrayList<Double> ()
	options.addAll(Arrays.asList(1,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,100))
	LengthParameter x = new LengthParameter(	args[0]+"_CaDoodle_CubeGeneration_X",
										20,options)
	LengthParameter y = new LengthParameter(	args[0]+"_CaDoodle_CubeGeneration_Y",
		20,options)
	LengthParameter z = new LengthParameter(	args[0]+"_CaDoodle_CubeGeneration_Z",
		20,options)
	ArrayList<Object> radOpts = []
	for(int i=0;i<Math.min(Math.min( y.getMM(),y.getMM()),z.getMM())/2;i++) {
		radOpts.add(i)
	}
	LengthParameter rad = new LengthParameter(	args[0]+"_CaDoodle_CubeGeneration_Round",
		0,radOpts)
	LengthParameter chamfer = new LengthParameter(	args[0]+"_CaDoodle_CubeGeneration_Chamfer",
		0,radOpts)
	CSG local = null
	if(rad.getMM()>0) {
		local=new RoundedCube(x,y,z).cornerRadius(rad.getMM()).toCSG()
		chamfer.setMM(0)
	}else if (chamfer.getMM()>0) {
		local=new ChamferedCube(x.getMM(),y.getMM(),z.getMM() ,chamfer.getMM()).toCSG()
		local.setParameter(x).setParameter(y).setParameter(z);
		rad.setMM(0);
	}else {
		local=new Cube(x,y,z ).toCSG()
	}
	
	CSG cube =  local.toZMin().setColor(Color.RED)
	CSGDatabase.saveDatabase();
	return cube
		.setParameter(rad)
		.setParameter(chamfer)
		.setRegenerate({getObject()})
}
return getObject()

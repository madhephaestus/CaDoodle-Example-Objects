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
	ArrayList<Double> optionsSides = new  ArrayList<Double> ()
	optionsSides.addAll(Arrays.asList(6,7,8,9,10,11,12,13,14,15,16,17,18,19,32))
	LengthParameter x = new LengthParameter(	args[0]+"_CaDoodle_CubeGeneration_Y",
			20,options)
	LengthParameter y = new LengthParameter(	args[0]+"_CaDoodle_CubeGeneration_X",
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
	LengthParameter sides = new LengthParameter(	args[0]+"_CaDoodle_CubeGeneration_Sides",
		8,optionsSides)
	CSG local = null
	try {
		if(rad.getMM()>0.01) {
			if(z.getMM()<=rad.getMM()*2)
				z.setMM(rad.getMM()*2+0.1)
			if(x.getMM()<=rad.getMM()*2)
				x.setMM(rad.getMM()*2+0.1)
			if(y.getMM()<=rad.getMM()*2)
				y.setMM(rad.getMM()*2+0.1)
			RoundedCube cornerRadius = new RoundedCube(x,y,z).cornerRadius(rad.getMM())
			cornerRadius.setResolution((int)sides.getMM())
			local=cornerRadius.toCSG()
			chamfer.setMM(0)
		}else if (chamfer.getMM()>0.01) {
			if(z.getMM()<=chamfer.getMM()*2)
				z.setMM(chamfer.getMM()*2+0.1)
			if(x.getMM()<=chamfer.getMM()*2)
				x.setMM(chamfer.getMM()*2+0.1)
			if(y.getMM()<=chamfer.getMM()*2)
				y.setMM(chamfer.getMM()*2+0.1)
			local=new ChamferedCube(x.getMM(),y.getMM(),z.getMM() ,chamfer.getMM()).toCSG()
			local.setParameter(x).setParameter(y).setParameter(z);
			rad.setMM(0);
		}
		if(local==null)
			local=new Cube(x,y,z ).toCSG()
	}catch(Throwable t) {
		t.printStackTrace()
	}
	if(local==null) {
		local=new Cube(20,20,20 ).toCSG()
		x.setMM(20)
		y.setMM(20)
		z.setMM(20)
		rad.setMM(0)
		chamfer.setMM(0)
	}

	CSG cube =  local.toZMin().setColor(Color.RED).setIsHole(true)
	CSGDatabase.saveDatabase();
	return cube
			.setParameter(rad)
			.setParameter(chamfer)
			.setParameter(sides)
			.setRegenerate({getObject()})
}
return getObject()

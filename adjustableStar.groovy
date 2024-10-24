import  eu.mihosoft.vrl.v3d.ext.quickhull3d.*
import eu.mihosoft.vrl.v3d.parametrics.CSGDatabase
import eu.mihosoft.vrl.v3d.parametrics.LengthParameter
import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.Transform
import eu.mihosoft.vrl.v3d.Vector3d
import eu.mihosoft.vrl.v3d.*
import javafx.scene.paint.Color
import eu.mihosoft.vrl.v3d.*
import javafx.scene.paint.Color

import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.Cube
import javafx.scene.paint.Color
CSG getObject(){
	if(args==null)
		args=["Test_key_here"]
	ArrayList<Double> options = new  ArrayList<Double> ()
	options.addAll(Arrays.asList(3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20))
	LengthParameter word = new LengthParameter(	args[0]+"_CaDoodle_StarSide_Points",
	6,options)
	LengthParameter top = new LengthParameter(	args[0]+"_CaDoodle_StarTop_Outer",
	20,[])
	LengthParameter bottom = new LengthParameter(	args[0]+"_CaDoodle_StarBottom_Inner",
	0.5,[0.0,0.25,0.5,0.75,0.9,1.0])
	int getMM = (int)word.getMM()
	int numberOfPoints = getMM;
	double angle = 360.0/numberOfPoints;
	double totalRad = top.getMM()
	double innerPercentage = bottom.getMM()
	double innerRad = totalRad*innerPercentage;
	def parts = []
	for(double i=0;i<360;i+=angle) {
		def points = [
			new Vector3d(totalRad, 0, 0),
			new Vector3d(0, 0, 10),
			new Vector3d(innerRad, 0, 0).transform(new Transform().rotz(angle/2.0)),
			new Vector3d(innerRad, 0, 0).transform(new Transform().rotz(-angle/2.0)),
			new Vector3d(0, 0, 0)
		]
		parts.add( HullUtil.hull(points).rotz(i))
	}
	CSG star = CSG.unionAll(parts)
 	CSGDatabase.saveDatabase();
	return star
	.setParameter(word)
	.setParameter(top)
	.setParameter(bottom)
	.setRegenerate({
		getObject()
	})
}
return getObject()
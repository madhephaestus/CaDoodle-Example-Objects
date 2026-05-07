import eu.mihosoft.vrl.v3d.parametrics.CSGDatabase
import eu.mihosoft.vrl.v3d.parametrics.CSGDatabaseInstance
import eu.mihosoft.vrl.v3d.parametrics.LengthParameter
import eu.mihosoft.vrl.v3d.Vector3d
import eu.mihosoft.vrl.v3d.*
import javafx.scene.paint.Color
CSG getObject(CSGDatabaseInstance csgdb){
	if(args==null)
		args=["Test_key_here"]
	ArrayList<Double> options = new  ArrayList<Double> ()
	options.addAll(Arrays.asList(3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20))
	LengthParameter word = new LengthParameter(csgdb,	args[0]+"_CaDoodle_StarSide_Points",
	5,options)
	LengthParameter top = new LengthParameter(csgdb,	args[0]+"_CaDoodle_StarTop_Outer",
	20,[])
	LengthParameter bottom = new LengthParameter(csgdb,	args[0]+"_CaDoodle_StarBottom_Inner",
	0.5,[0.1,0.25,0.5,0.75,0.9,1.0])
	int getMM = (int)word.getMM()
	int numberOfPoints = getMM;
	double totalRad = top.getMM()/2
	double innerPercentage = bottom.getMM()
	double innerRad = totalRad*innerPercentage;
    double angle = 360.0 / numberOfPoints
    ArrayList<Vertex> perimeter = new ArrayList<>()
    for (int i = 0; i < numberOfPoints; i++) {
		
        double outerAngle = Math.toRadians(i * angle)
        double innerAngle = Math.toRadians(i * angle + angle / 2.0)
        // Outer point (tip of spike)
        perimeter.add(new Vertex(
            totalRad * Math.cos(outerAngle),
            totalRad * Math.sin(outerAngle),
            0.0
        ))
        // Inner point (valley between spikes)
        perimeter.add(new Vertex(
            innerRad * Math.cos(innerAngle),
            innerRad * Math.sin(innerAngle),
            0.0
        ))
    }
	Vector3d extrudeDir = new Vector3d(0, 0, 10);
	CSG star =Extrude.extrude(extrudeDir, new Polygon(perimeter))
	return star.setColor(Color.YELLOW)
	.setParameter(csgdb,word)
	.setParameter(csgdb,top)
	.setParameter(csgdb,bottom)
	.setRegenerate({
		getObject()
	})
}
return getObject(csgdb)
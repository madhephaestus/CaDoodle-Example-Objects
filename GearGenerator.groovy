import com.neuronrobotics.bowlerstudio.scripting.ScriptingEngine

import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.parametrics.StringParameter
import javafx.scene.paint.Color
import eu.mihosoft.vrl.v3d.parametrics.CSGDatabase;
import eu.mihosoft.vrl.v3d.parametrics.LengthParameter
if(args==null)
	args=["Test_key_here"]
double computeGearPitch(double diameterAtCrown,double numberOfTeeth){
	return ((diameterAtCrown/2)*((360.0)/numberOfTeeth)*Math.PI/180)
}
def getObject(){
	ArrayList<Number> angles = new ArrayList<>();
	for(int i=0;i<90;i++) {
		angles.add(i);
	}
	ArrayList<Number> options = new ArrayList<>();
	for(int i=20;i<100;i++) {
		options.add(i);
	}
	LengthParameter ateeth = new LengthParameter(csgdb,	args[0]+"_CaDoodle_A-Teeth",34,options)
	LengthParameter bteeth = new LengthParameter(csgdb,	args[0]+"_CaDoodle_B-Teeth",24,options)
	LengthParameter adiam = new LengthParameter(csgdb,	args[0]+"_CaDoodle_A-Diam",26.15,options)
	LengthParameter angle = new LengthParameter(csgdb,	args[0]+"_CaDoodle_Angle",90,angles)
	
	// call a script from another library
	List bevelGears = ScriptingEngine.gitScriptRun(
	"https://github.com/madhephaestus/GearGenerator.git", // git location of the library
	"bevelGear.groovy" , // file to load	
	// Parameters passed to the funcetion
	[	  ateeth.getMM(),// Number of teeth gear a
		bteeth.getMM(),// Number of teeth gear b
		3,// thickness of gear A
		computeGearPitch(adiam.getMM(),ateeth.getMM()),// gear pitch in arc length mm
		angle.getMM(),// shaft angle, can be from 0 to 100 degrees
		0// helical angle, only used for 0 degree bevels
	]
	)
	//Print parameters returned by the script
	println "Bevel gear axil center to center " + bevelGears.get(2)
	println "Bevel gear axil Height " + bevelGears.get(3)
	println "Bevel angle " + bevelGears.get(4)
	println "Bevel tooth face length " + bevelGears.get(5)
	println "Gear B computed thickness " + bevelGears.get(6)
	println "Gear Ratio " + bevelGears.get(7)
	println "Mesh Interference calculated: " + bevelGears.get(9)
	// return the CSG parts
	CSG agear = bevelGears.get(0);
	CSG bgear = bevelGears.get(1);
	agear.setRegenerate({
		getObject().get(0);
	})
	bgear.setRegenerate({
		getObject().get(1)
	})
	ArrayList<CSG> back = [agear,bgear];
	
	agear.setColor(Color.SILVER)
	bgear.setColor(Color.GREY)
	for(CSG c:back) {
		c.setParameter(csgdb,ateeth)
		.setParameter(csgdb,bteeth)
		.setParameter(csgdb,adiam)
		.setParameter(csgdb,angle)
	}
	
	return back

}
return getObject()
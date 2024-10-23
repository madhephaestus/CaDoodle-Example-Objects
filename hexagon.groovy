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
	CSG text =  new Hexagon(20,20).toCSG().toZMin().setColor(Color.DARKBLUE)
	CSGDatabase.saveDatabase();
	return text
		.setRegenerate({getObject()})
}
return getObject()

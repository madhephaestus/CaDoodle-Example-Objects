import eu.mihosoft.vrl.v3d.*
import javafx.scene.paint.Color
import eu.mihosoft.vrl.v3d.*
import javafx.scene.paint.Color
import javafx.scene.paint.Color

import eu.mihosoft.vrl.v3d.parametrics.*;
CSG getObject(){
	if(args==null)
		args=["Test_key_here"]
	CSG text =  Parabola.cone(10,20).toZMin().setColor(Color.WHITE)
	return text
		.setRegenerate({getObject()})
}
return getObject()

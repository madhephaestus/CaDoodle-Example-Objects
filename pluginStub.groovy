import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.Cube
import javafx.scene.paint.Color

CSG part= new Cube(20).toCSG().toZMin().setColor(Color.RED);
return part;

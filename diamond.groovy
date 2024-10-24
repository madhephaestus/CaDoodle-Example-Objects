import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.Cube
import eu.mihosoft.vrl.v3d.Cylinder
import javafx.scene.paint.Color
CSG base =new Cylinder(10,7.5,3,8).toCSG().movez(7)
		.union(new Cube(0.001).toCSG().toZMin())
		.hull()

return base.toZMin().setColor(Color.LIGHTBLUE)

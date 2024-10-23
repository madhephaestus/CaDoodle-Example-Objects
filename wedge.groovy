import eu.mihosoft.vrl.v3d.Cube
import eu.mihosoft.vrl.v3d.Isosceles
import eu.mihosoft.vrl.v3d.Wedge
import javafx.scene.paint.Color

return new Wedge(20,20,20).toCSG().toZMin().setColor(Color.DARKBLUE)

import eu.mihosoft.vrl.v3d.Cube
import eu.mihosoft.vrl.v3d.Isosceles
import javafx.scene.paint.Color

return new Isosceles(20,20,10).toCSG().toZMin().setColor(Color.GREEN)

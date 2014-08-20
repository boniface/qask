val name = List(10, 50)
val values = List(1,2,3)

 name ::: values

println("The output  as is "+name)
import java.net._

val localhost = InetAddress.getLocalHost

val host = localhost.getHostName

val ip = localhost.getHostAddress

object Margin extends Enumeration {
  type Margin = Value
  val TOP, BOTTOM, LEFT, RIGHT = Value
}

val top = Margin.BOTTOM.toString
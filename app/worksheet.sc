val name = List(10, 50)
val values = List(1,2,3)

 name ::: values

println("The output  as is "+name)
import java.net._
import org.joda.time.{DateTimeConstants, LocalDate, DateTime}
val localhost = InetAddress.getLocalHost
val host = localhost.getHostName
val ip = localhost.getHostAddress
object Margin extends Enumeration {
  type Margin = Value
  val TOP, BOTTOM, LEFT, RIGHT = Value
}


val  today = DateTime.now().toLocalDate;
println("Today is Date ", today.toDate)
println("Yesterday was Date ", today.minusDays(1).toDate)

println("THIS WEEK IS START",today.withDayOfWeek(DateTimeConstants.SUNDAY).minusDays(7).toDate)
println(" Start of the Month", today.dayOfMonth.withMinimumValue())
val res = test("hello")

def test(value:String) = {
  value match {
    case "hello"=> "Hello"
    case "world"=> "World"
    case _=> "Doesn't Match"
  }

}




/**
  * Created by Akhilanand BV on 6/19/2017.
  */
import scala.collection.mutable
import scala.collection.mutable._
import scala.io.StdIn._

object SchoolApp extends App {
println("Hello World!!")


  trait studentEvent
  trait enrollmentEvent

  var courses: HashSet[String] = HashSet[String]("CS101","CS201","CS401","CS430")
  //   var students: HashSet[String] =  HashSet[String]("Akhil")
  var students: HashSet[String] =  HashSet[String]()
  var enrollment: HashMap[String, ArrayBuffer[String]] =  HashMap[String, ArrayBuffer[String]]()//{Subject:{Students}}
 /* println(courses)
  println(students)
  print(enrollment)*/

  case class addStudent(studentName: String) extends studentEvent

  case class addClass(className: String) extends enrollmentEvent
  case class enrollClass(studentName: String, className: String) extends enrollmentEvent
  case class cancelEnrollment(studentName: String, className: String) extends enrollmentEvent
  case class removeClass(className: String) extends enrollmentEvent
  object eventsHandler {
    def studentActivity( event :studentEvent): Unit={
      event match{
        case addStudent(studentName: String) => students.add(studentName)//Check if the event triggered is of addStudent

      }//End of event Match
    }//End of studentActivity method

    def enrollmentActivity( event :enrollmentEvent): Unit={
      event match{
        case addClass(className: String)  => courses.add(className)//Check if the event triggered is of addClass
        case removeClass(className: String)  =>{
          enrollment.remove(className)
          courses.add(className)
        }
        case enrollClass(studentName: String, className: String)=>{
          println("Please make sure you are not already enrolled for this class")

          var studentsList=enrollment.getOrElse(className, new ArrayBuffer[String]())
          studentsList+=studentName

          enrollment.put(className,studentsList)
        }

        case cancelEnrollment(studentName: String, className: String)=>{
          println("Please make sure you are not already enrolled for this class")

          var studentsList=enrollment.getOrElse(className, new ArrayBuffer[String]())
          studentsList-=studentName

          enrollment.put(className,studentsList)
        }
      }//End of event Match
    }//End of enrollmentActivity method



  }//End of eventsHandler


  //Switch statements to take input from user
  var option = 1
  var input= 1
  while (input != 0) {
    println("Welcome to our school. Choose a below action")
    println(" 1.List all the courses available")
    println(" 2.List all the students")
    println(" 3.Enrollment of course")
    println(" 4.Cancellation of Enrollment of course")
    println(" 5.Cancel a class")
    println(" 6.Add a student")
    println("7. Add New Class")


    input = readInt()

    input match {
      case 0 => println("Exiting!!")
      case 1 => println(courses)
      case 2 =>println(students)
      case 3 =>{
        println("Enter student name to be enrolled")
        val studentName = readLine()
        println("Enter class name to be enrolled")
        val className = readLine()
        eventsHandler.enrollmentActivity(event= enrollClass(studentName, className))
      }

      case 4 =>{
        println("Enter student name to be removed from enrollment ")
        val studentName = readLine()
        println("Enter class name to be removed from enrollment")
        val className = readLine()
        eventsHandler.enrollmentActivity(event= cancelEnrollment(studentName, className))
      }
      case 5 => {
        println("Enter class name to be cancelled")
        val className = readLine()
        eventsHandler.enrollmentActivity(event= removeClass(className))
      }

      case 6 => {
        println("Enter the student name to be added")
        val studentName = readLine()
        println("Students")
        eventsHandler.studentActivity(event= addStudent(studentName))
        println(students)
      }//End of Case for Option 6

      case 7 =>{
        println("Available courses for enrollment")
        println(courses)
        println("Enter class name to be added")
        val className = readLine()
        eventsHandler.enrollmentActivity(event= addClass(className))
        println(courses)
      }
    }




  }//End of while


}//end of Main Object

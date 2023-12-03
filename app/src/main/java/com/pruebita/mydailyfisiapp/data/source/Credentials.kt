package com.pruebita.mydailyfisiapp.data.source

import com.pruebita.mydailyfisiapp.data.model.domain.Course
import com.pruebita.mydailyfisiapp.data.model.domain.DailyCourseAssist
import com.pruebita.mydailyfisiapp.data.model.domain.Event
import com.pruebita.mydailyfisiapp.data.model.domain.Reminder
import com.pruebita.mydailyfisiapp.data.model.domain.Room
import com.pruebita.mydailyfisiapp.data.model.domain.StudentAssistUnit
import com.pruebita.mydailyfisiapp.data.model.domain.StudentGlobalReport
import com.pruebita.mydailyfisiapp.data.model.domain.SubPart
import com.pruebita.mydailyfisiapp.data.model.domain.TeacherGlobalReport
import com.pruebita.mydailyfisiapp.data.model.domain.User
import com.pruebita.mydailyfisiapp.data.model.domain.UsuariosApi
import kotlinx.datetime.LocalDateTime
import java.util.Calendar
import java.util.TimeZone

val credentials = UsuariosApi(
    Codigo = 1,
    UserName = "jperez",
    Clave = ByteArray(0),
    Clavetxt = "123456",
    Nombre = "Juan Perez",
    Rol = "1"
)

var student1= User(
    idUser = 1,
    idRol = 1,
    user = "nabia.pachas",
    password = "123456",
    names = "Nabia Jasmin",
    firstLastName = "Pachas",
    secondLastName = "Lopez",
    email = "nabia.pachas@unmsm.edu.pe",
    cellphone = "950415842",
    imageUser = "https://firebasestorage.googleapis.com/v0/b/dailyfisiapp.appspot.com/o/users%2Fprofiles%2Fuser_1.jpg?alt=media&token=8fa61ee1-f687-4e43-8cab-f799bfd58f36",
    sessionActive = false,
    userActive = false
)

var student2= User(
    idUser = 2,
    idRol = 1,
    user = "kevinmiguel.ortiz",
    password = "123456",
    names = "Kevin Miguel",
    firstLastName = "Ortiz",
    secondLastName = "Abanto",
    email = "kevinmiguel.ortiz@unmsm.edu.pe",
    cellphone = "950415842",
    imageUser = "https://firebasestorage.googleapis.com/v0/b/dailyfisiapp.appspot.com/o/users%2Fprofiles%2Fuser_2.jpg?alt=media&token=4fbbf795-91fe-4e3b-a738-2afcce491c2d",
    sessionActive = false,
    userActive = false
)

var teacher1 = User(
    idUser = 5,
    idRol = 3,
    user = "rubendavid.robles",
    password = "123456",
    names = "Ruben David",
    firstLastName = "Robles",
    secondLastName = "Matienzo",
    email = "rubendavid.robles@unmsm.edu.pe",
    cellphone = "950415842",
    imageUser = "https://firebasestorage.googleapis.com/v0/b/dailyfisiapp.appspot.com/o/users%2Fprofiles%2Fuser_5.png?alt=media&token=16ef45f6-b4ed-4cce-a398-9a28e9a8da4a",
    sessionActive = false,
    userActive = false
)

val salon1 = Room(
    idRoom = 208,
    typeRoom = "Aula",
    pavilion = "AP",
    floor = 2,
    posX = 40,
    posY = 135,
)

val lab1 = Room(
    idRoom = 2,
    typeRoom = "Lab",
    pavilion = "NP",
    floor = 2,
    posX = 50,
    posY = 70,
)

var event1 = Event(
    idEvent = 1,
    idUser = 1,
    title = "Hackathon",
    shortDescription = "El día de hoy se presentaran 5 grupos en el Auditorio.",
    longDescription = "El día de hoy se presentaran 5 grupos en el Auditorio.",
    eventImage = "https://firebasestorage.googleapis.com/v0/b/dailyfisiapp.appspot.com/o/events%2Fimages_events%2Fevent_1.png?alt=media&token=f7ea7c49-6689-4b7c-9208-0f7c8154bb1f",
    eventDate = getDate(2023,11,1,15,0),
    place = "Auditorio FISI",
    address = "Av. Germán Amézaga s/n. Ciudad Universitaria",
    publisherName = "Nabia Pachas",
    publisherImage = "https://firebasestorage.googleapis.com/v0/b/dailyfisiapp.appspot.com/o/users%2Fprofiles%2Fuser_1.jpg?alt=media&token=8fa61ee1-f687-4e43-8cab-f799bfd58f36",
    category = "Estudios",
    publishingDate = getDate(2023,10,15,16,0),
)

var event2 = Event(
    idEvent = 2,
    idUser = 1,
    title = "Chocolatada Navideña",
    shortDescription = "Disfruta de esta chocolatada 2023.",
    longDescription = "El dia 2 de Diciembre se realizará la Chocolatada San Marquina en el comedor.",
    eventImage = "https://firebasestorage.googleapis.com/v0/b/dailyfisiapp.appspot.com/o/events%2Fimages_events%2Fevent_2.png?alt=media&token=03a24f65-67a3-46f9-9038-e263c5165a0d",
    eventDate = getDate(2023,11,2,12,0),
    place = "Comedor UNMSM",
    address = "Av. Germán Amezaga s/n. Ciudad Universitaria",
    publisherName = "Nabia Pachas",
    publisherImage = "https://firebasestorage.googleapis.com/v0/b/dailyfisiapp.appspot.com/o/users%2Fprofiles%2Fuser_1.jpg?alt=media&token=8fa61ee1-f687-4e43-8cab-f799bfd58f36",
    category = "Ocio",
    publishingDate = getDate(2023,10,18,16,0),
)
var event3 = Event(
    idEvent = 3,
    idUser = 1,
    title = "Conferencia BCP",
    shortDescription = "Unete al JCONF-2023.",
    longDescription = "¡Somos parte de la JFCONG 2023! Este sábado 02 de diciembre, forma parte del congreso más grande de JAVA en Perú en donde podrás participar de talleres, presentaciones, demostraciones en vivo sobre las últimas tendencias tecnológicas y profundizar en las tecnologías del ecosistema de Java de la mano de spakers nacionales e internacionales.",
    eventImage = "https://firebasestorage.googleapis.com/v0/b/dailyfisiapp.appspot.com/o/events%2Fimages_events%2Fevent_3.png?alt=media&token=55e98383-bda2-49b0-849f-9ac3603c1973",
    eventDate = getDate(2023,11,2,12,0),
    place = "Edificio SOHO+",
    address = "Av. Tomas Ramsey 930, Magdalena del Mar",
    publisherName = "Nabia Pachas",
    publisherImage = "https://firebasestorage.googleapis.com/v0/b/dailyfisiapp.appspot.com/o/users%2Fprofiles%2Fuser_1.jpg?alt=media&token=8fa61ee1-f687-4e43-8cab-f799bfd58f36",
    category = "Estudios",
    publishingDate = getDate(2023,10,2,16,0),
)


var sd = Course(
    idCourse = 1,
    courseName = "Sistemas Distribuidos",
    startDate = getDate(2023, 9,30,15,0),
    endDate = getDate(2024, 1,12,18,0),
    term = "VIII",
    theoryPart = SubPart(
        idPart = 0,
        desPart = "Teoria",
        startHour = getTime(15,0),
        endHour= getTime(16,30),
        room = salon1
    ),
    labPart = SubPart(
        idPart = 1,
        desPart = "Practica",
        startHour = getTime(16,30),
        endHour= getTime(18,0),
        room = lab1
    ),
    teacherFullName = "Alberto Coronado Mestanza",
    section = 1
)

var met = Course(
    idCourse = 2,
    courseName = "Metodología",
    startDate = getDate(2023, 9,30,19,0),
    endDate = getDate(2024, 1,12,22,0),
    term = "VIII",
    theoryPart = SubPart(
        idPart = 0,
        desPart = "Teoria",
        startHour = getTime(19,0),
        endHour= getTime(20,0),
        room = salon1
    ),
    labPart = SubPart(
        idPart = 1,
        desPart = "Practica",
        startHour = getTime(20,0),
        endHour= getTime(22,0),
        room = lab1
    ),
    teacherFullName = "Frank Escobedo Manrique",
    section = 1
)

var ingInfo = Course(
    idCourse = 3,
    courseName = "Ing. Informacion",
    startDate = getDate(2023, 9,31,13,0),
    endDate = getDate(2024, 1,13,18,0),
    term = "VIII",
    theoryPart = SubPart(
        idPart = 0,
        desPart = "Teoria",
        startHour = getTime(13,0),
        endHour= getTime(16,0),
        room = salon1
    ),
    labPart = SubPart(
        idPart = 1,
        desPart = "Practica",
        startHour = getTime(16,0),
        endHour= getTime(18,0),
        room = lab1
    ),
    teacherFullName = "Percy de la Cruz Villa",
    section = 1
)

var si = Course(
    idCourse = 4,
    courseName = "Sistemas Inteligentes",
    startDate = getDate(2023, 9,31,18,0),
    endDate = getDate(2024, 1,13,22,0),
    term = "VIII",
    theoryPart = SubPart(
        idPart = 0,
        desPart = "Teoria",
        startHour = getTime(18,0),
        endHour= getTime(20,0),
        room = salon1
    ),
    labPart = SubPart(
        idPart = 1,
        desPart = "Practica",
        startHour = getTime(20,0),
        endHour= getTime(22,0),
        room = lab1
    ),
    teacherFullName = "Rolando Maguiña Perez",
    section = 1
)

var gdp = Course(
    idCourse = 5,
    courseName = "Gestion de Proyectos",
    startDate = getDate(2023, 10,1,19,0),
    endDate = getDate(2024, 1,14,22,0),
    term = "VIII",
    theoryPart = SubPart(
        idPart = 0,
        desPart = "Teoria",
        startHour = getTime(19,0),
        endHour= getTime(20,30),
        room = salon1
    ),
    labPart = SubPart(
        idPart = 1,
        desPart = "Practica",
        startHour = getTime(20,30),
        endHour= getTime(22,0),
        room = lab1
    ),
    teacherFullName = "Cayo Victor Leon Fernandez",
    section = 1
)

var audi = Course(
    idCourse = 6,
    courseName = "Auditoria",
    startDate = getDate(2023, 10,3,22,30),
    endDate = getDate(2024, 1,16,23,30),
    term = "VIII",
    theoryPart = SubPart(
        idPart = 0,
        desPart = "Teoria",
        startHour = getTime(22,30),
        endHour= getTime(23,0),
        room = salon1
    ),
    labPart = SubPart(
        idPart = 1,
        desPart = "Practica",
        startHour = getTime(23,0),
        endHour= getTime(23,30),
        room = lab1
    ),
    teacherFullName = "Nilo Carrasco Ore",
    section = 1
)

var moviles = Course(
    idCourse = 7,
    courseName = "Moviles",
    startDate = getDate(2023, 10,4,8,0),
    endDate = getDate(2024, 1,17,12,0),
    term = "VIII",
    theoryPart = SubPart(
        idPart = 0,
        desPart = "Teoria",
        startHour = getTime(8,0),
        endHour= getTime(10,0),
        room = salon1
    ),
    labPart = SubPart(
        idPart = 1,
        desPart = "Practica",
        startHour = getTime(10,0),
        endHour= getTime(12,0),
        room = lab1
    ),
    teacherFullName = "Ivan Carlo Petrilk",
    section = 1
)

var calculo = Course(
    idCourse = 8,
    courseName = "Calculo I",
    startDate = getDate(2023, 10,4,14,0),
    endDate = getDate(2024, 1,17,18,0),
    term = "VIII",
    theoryPart = SubPart(
        idPart = 0,
        desPart = "Teoria",
        startHour = getTime(14,0),
        endHour= getTime(16,0),
        room = salon1
    ),
    labPart = SubPart(
        idPart = 1,
        desPart = "Practica",
        startHour = getTime(16,0),
        endHour= getTime(18,0),
        room = lab1
    ),
    teacherFullName = "Ruben David Robles Matienzo",
    section = 1
)

var mkt = Course(
    idCourse = 9,
    courseName = "Marketing",
    startDate = getDate(2023, 10,4,18,0),
    endDate = getDate(2024, 1,17
        ,22,0),
    term = "VIII",
    theoryPart = SubPart(
        idPart = 0,
        desPart = "Teoria",
        startHour = getTime(18,0),
        endHour= getTime(20,0),
        room = salon1
    ),
    labPart = SubPart(
        idPart = 1,
        desPart = "Practica",
        startHour = getTime(20,0),
        endHour= getTime(22,0),
        room = lab1
    ),
    teacherFullName = "Ruben David Robles Matienzo",
    section = 1
)

val student1Global = StudentGlobalReport(
    assSd = mutableListOf(
        DailyCourseAssist(getDate(2023,9,30,3,15), theoryAssist = false, labAssist = true),
        DailyCourseAssist(getDate(2023,10,6,3,15), theoryAssist = true, labAssist = true),
        DailyCourseAssist(getDate(2023,10,13,3,15), theoryAssist = true, labAssist = true),
        DailyCourseAssist(getDate(2023,10,20,3,15), theoryAssist = true, labAssist = true),
        DailyCourseAssist(getDate(2023,10,27,3,15), theoryAssist = true, labAssist = true),
    ),
    assMet = mutableListOf(
        DailyCourseAssist(getDate(2023,9,30,7,15), theoryAssist = true, labAssist = true),
        DailyCourseAssist(getDate(2023,10,6,7,15), theoryAssist = false, labAssist = false),
        DailyCourseAssist(getDate(2023,10,13,7,15), theoryAssist = true, labAssist = true),
        DailyCourseAssist(getDate(2023,10,20,7,15), theoryAssist = true, labAssist = true),
        DailyCourseAssist(getDate(2023,10,27,7,15), theoryAssist = true, labAssist = true),
    ) ,
    assIng = mutableListOf(
        DailyCourseAssist(getDate(2023,9,31,7,15), theoryAssist = true, labAssist = true),
        DailyCourseAssist(getDate(2023,10,7,7,15), theoryAssist = true, labAssist = true),
        DailyCourseAssist(getDate(2023,10,14,7,15), theoryAssist = false, labAssist = true),
        DailyCourseAssist(getDate(2023,10,21,7,15), theoryAssist = true, labAssist = false),
        DailyCourseAssist(getDate(2023,10,28,7,15), theoryAssist = true, labAssist = true),
    ) ,
    assSi = mutableListOf(
        DailyCourseAssist(getDate(2023,9,31,7,15), theoryAssist = true, labAssist = true),
        DailyCourseAssist(getDate(2023,10,7,7,15), theoryAssist = true, labAssist = true),
        DailyCourseAssist(getDate(2023,10,14,7,15), theoryAssist = true, labAssist = false),
        DailyCourseAssist(getDate(2023,10,21,7,15), theoryAssist = true, labAssist = false),
        DailyCourseAssist(getDate(2023,10,28,7,15), theoryAssist = true, labAssist = false),
    ) ,
    assGdp = mutableListOf(
        DailyCourseAssist(getDate(2023,10,1,7,15), theoryAssist = true, labAssist = true),
        DailyCourseAssist(getDate(2023,10,8,7,15), theoryAssist = true, labAssist = true),
        DailyCourseAssist(getDate(2023,10,15,7,15), theoryAssist = true, labAssist = true),
        DailyCourseAssist(getDate(2023,10,22,7,15), theoryAssist = true, labAssist = true),
        DailyCourseAssist(getDate(2023,10,29,7,15), theoryAssist = true, labAssist = false),
    ) ,
    assAudi = mutableListOf(
        DailyCourseAssist(getDate(2023,10,3,7,15), theoryAssist = true, labAssist = true),
        DailyCourseAssist(getDate(2023,10,10,7,15), theoryAssist = true, labAssist = true),
        DailyCourseAssist(getDate(2023,10,17,7,15), theoryAssist = true, labAssist = true),
        DailyCourseAssist(getDate(2023,10,24,7,15), theoryAssist = false, labAssist = true),
        DailyCourseAssist(getDate(2023,11,1,7,15), theoryAssist = false, labAssist = true),
    ) ,
    assMoviles = mutableListOf(
        DailyCourseAssist(getDate(2023,10,4,7,15), theoryAssist = true, labAssist = true),
        DailyCourseAssist(getDate(2023,10,11,7,15), theoryAssist = true, labAssist = true),
        DailyCourseAssist(getDate(2023,10,18,7,15), theoryAssist = true, labAssist = true),
        DailyCourseAssist(getDate(2023,10,25,7,15), theoryAssist = true, labAssist = true),
        DailyCourseAssist(getDate(2023,11,2,7,15), theoryAssist = null, labAssist = null),
    ) ,
    assCalculo = mutableListOf(
        DailyCourseAssist(getDate(2023,10,4,7,15), theoryAssist = true, labAssist = false),
        DailyCourseAssist(getDate(2023,10,11,7,15), theoryAssist = false, labAssist = false),
        DailyCourseAssist(getDate(2023,10,18,7,15), theoryAssist = true, labAssist = false),
        DailyCourseAssist(getDate(2023,10,25,7,15), theoryAssist = true, labAssist = true),
        DailyCourseAssist(getDate(2023,11,2,7,15), theoryAssist = null, labAssist = null),
    ) ,
    assMkt = mutableListOf(
        DailyCourseAssist(getDate(2023,10,4,7,15), theoryAssist = true, labAssist = false),
        DailyCourseAssist(getDate(2023,10,11,7,15), theoryAssist = true, labAssist = true),
        DailyCourseAssist(getDate(2023,10,18,7,15), theoryAssist = true, labAssist = true),
        DailyCourseAssist(getDate(2023,10,25,7,15), theoryAssist = true, labAssist = true),
        DailyCourseAssist(getDate(2023,11,2,7,15), theoryAssist = null, labAssist = null),
    )
)

val teacherGlobalReportCalculo = TeacherGlobalReport(
    studentList = mutableListOf(
        StudentAssistUnit(
            idStudent = student1.idUser,
            names = student1.names,
            lastNames = student1.firstLastName + student1.secondLastName,
            nick = "${student1.names[0]} ${student1.firstLastName[0]}",
        ),
        StudentAssistUnit(
            idStudent = student2.idUser,
            names = student2.names,
            lastNames = student2.firstLastName + student2.secondLastName,
            nick = "${student2.names[0]} ${student2.firstLastName[0]}",
        ),
        StudentAssistUnit(
            idStudent = 3,
            names = "Marco Antonio",
            lastNames = "Escalante Arirama",
            nick = "ME",
        ), StudentAssistUnit(
            idStudent = 4,
            names = "Freddy",
            lastNames = "Flores Dilas",
            nick = "FF",
        ), StudentAssistUnit(
            idStudent = 5,
            names = "Piero Alexander",
            lastNames = "Castro Moran",
            nick = "PC",
        ), StudentAssistUnit(
            idStudent = 6,
            names = "Mireya Lucia",
            lastNames = "Jimenez De la Cruz",
            nick = "MJ",
        ), StudentAssistUnit(
            idStudent = 7,
            names = "Andrea Karin",
            lastNames = "Miranda Sanchez",
            nick = "AM",
        ), StudentAssistUnit(
            idStudent = 8,
            names = "Grissell Lizeth",
            lastNames = "Meza Iglesias",
            nick = "GM",
        ), StudentAssistUnit(
            idStudent = 9,
            names = "Mia Solimar",
            lastNames = "Acosta Cortez",
            nick = "MA",
        )
    ), theoryAssist = mutableListOf(3,3,4,4,4,2,4,2,4), labAssist = mutableListOf(1,1,3,4,3,3,1,2,3)
)

val teacherGlobalReportMkt = TeacherGlobalReport(
    studentList = mutableListOf(
        StudentAssistUnit(
            idStudent = student1.idUser,
            names = student1.names,
            lastNames = student1.firstLastName + student1.secondLastName,
            nick = "${student1.names[0]} ${student1.firstLastName[0]}",
        ),
        StudentAssistUnit(
            idStudent = student2.idUser,
            names = student2.names,
            lastNames = student2.firstLastName + student2.secondLastName,
            nick = "${student2.names[0]} ${student2.firstLastName[0]}",
        ),
        StudentAssistUnit(
            idStudent = 3,
            names = "Marco Antonio",
            lastNames = "Escalante Arirama",
            nick = "ME",
        ), StudentAssistUnit(
            idStudent = 4,
            names = "Freddy",
            lastNames = "Flores Dilas",
            nick = "FF",
        ), StudentAssistUnit(
            idStudent = 5,
            names = "Piero Alexander",
            lastNames = "Castro Moran",
            nick = "PC",
        ), StudentAssistUnit(
            idStudent = 6,
            names = "Mireya Lucia",
            lastNames = "Jimenez De la Cruz",
            nick = "MJ",
        ), StudentAssistUnit(
            idStudent = 7,
            names = "Andrea Karin",
            lastNames = "Miranda Sanchez",
            nick = "AM",
        ), StudentAssistUnit(
            idStudent = 8,
            names = "Grissell Lizeth",
            lastNames = "Meza Iglesias",
            nick = "GM",
        ), StudentAssistUnit(
            idStudent = 9,
            names = "Mia Solimar",
            lastNames = "Acosta Cortez",
            nick = "MA",
        )
    ), theoryAssist = mutableListOf(4,4,3,3,3,2,4,2,4), labAssist = mutableListOf(3,3,4,4,3,3,1,2,3)
)

var myRemainders = mutableListOf(
    Reminder(
        idReminder = 1,
        title = "Reunion de Gestion de Proyectos",
        dateStart = LocalDateTime(2023, 11, 27, 12, 30),
        dateEnd = LocalDateTime(2023, 11, 27, 13, 30),
        isDone = false
    ),
    Reminder(
        idReminder = 2,
        title = "Reunion de Calculo",
        dateStart = LocalDateTime(2023, 12, 2, 16, 30),
        dateEnd = LocalDateTime(2023, 12, 2, 18, 30),
        isDone = false
    ),
    Reminder(
        idReminder = 3,
        title = "Reunion de Algebra",
        dateStart = LocalDateTime(2023, 12, 2, 19, 30),
        dateEnd = LocalDateTime(2023, 12, 2, 20, 30),
        isDone = false
    )
)


fun getDate(year:Int, mes:Int,dia:Int,hour:Int, minute:Int): Calendar{
    val timeZone: TimeZone = TimeZone.getTimeZone("America/Lima")
    val calendar = Calendar.getInstance(timeZone)

    calendar.set(Calendar.YEAR, year)
    calendar.set(Calendar.MONTH, mes)
    calendar.set(Calendar.DAY_OF_MONTH, dia)
    calendar.set(Calendar.HOUR_OF_DAY, hour)
    calendar.set(Calendar.MINUTE, minute)
    return calendar
}

fun getTime(hour:Int, minute:Int): Calendar{
    val timeZone: TimeZone = TimeZone.getTimeZone("America/Lima")
    val calendar = Calendar.getInstance(timeZone)

    calendar.set(Calendar.HOUR_OF_DAY, hour)
    calendar.set(Calendar.MINUTE, minute)
    return calendar
}

import entity.Teacher;
import process.DBProcess;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Teacher teacher1 = new Teacher(7L, "Tofiq", "Mikayilzade", 2000F);
        Teacher teacher2 = new Teacher(8L, "Aiq", "Abushov", 1500F);
        Teacher teacher3 = new Teacher(9L, "Ramil", "Mirzeyev", 1200F);
        Teacher teacher4 = new Teacher(10L, "Anar", "Rzayev", 1000F);
        Teacher teacher5 = new Teacher(11L, "Tural", "Quliyev", 900F);
        Teacher teacher6 = new Teacher(12L, "Resul", "Musazade", 800F);

        List<Teacher> listOfTeachers = new ArrayList<>();
        listOfTeachers.add(teacher1);
        listOfTeachers.add(teacher2);
        listOfTeachers.add(teacher3);
        listOfTeachers.add(teacher4);
        listOfTeachers.add(teacher5);
        listOfTeachers.add(teacher6);

//        DBProcess.insertTeacher(listOfTeachers);

//        DBProcess.updateTeacher();
//        DBProcess.deleteTeacher();
        DBProcess.findStudentLikeName();

    }
}

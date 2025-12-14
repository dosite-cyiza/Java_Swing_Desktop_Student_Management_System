package service;

import model.Student;
import java.util.List;


public interface StudentService {
    
    
    boolean addStudent(Student student);
    
        List<Student> getAllStudents();
    
        boolean updateStudent(Student student);
    
        boolean deleteStudent(int studentId);
    
        Student getStudentById(int studentId);
}
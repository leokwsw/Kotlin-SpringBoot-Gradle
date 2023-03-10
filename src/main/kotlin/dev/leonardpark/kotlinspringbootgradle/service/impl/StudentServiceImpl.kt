package dev.leonardpark.kotlinspringbootgradle.service.impl

import dev.leonardpark.kotlinspringbootgradle.data.dao.StudentDao
import dev.leonardpark.kotlinspringbootgradle.data.entity.Student
import dev.leonardpark.kotlinspringbootgradle.service.StudentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class StudentServiceImpl(@Autowired val studentDao: StudentDao) : StudentService {
  override fun findAllStudent(): MutableList<Student> = studentDao.findAll()

  override fun addStudent(student: Student): Student =
    Student(
      name = student.name.trim(),
      email = student.email.trim()
    ).run {
      return studentDao.save(this)
    }

  override fun findByStudentId(id: Long): Optional<Student?> = studentDao.findById(id)

  override fun findByStudentName(name: String): MutableList<Student> = studentDao.findByName(name)

  override fun updateStudent(student: Student): Student =
    Student(
      id = student.id,
      name = student.name.trim(),
      email = student.email.trim()
    ).run {
      return studentDao.save(this)
    }

  override fun updateStudentEmail(student: Student): Student =
    Student(
      id = student.id,
      name = student.name,
      email = student.email.trim()
    ).run {
      return studentDao.save(this)
    }

  override fun deleteStudent(id: Long): Boolean =
    studentDao.findById(id)
      .run {
        this ?: false
      }.run {
        return try {
          studentDao.delete(this as Student)
          true
        } catch (exception: Exception) {
          false
        }
      }
}

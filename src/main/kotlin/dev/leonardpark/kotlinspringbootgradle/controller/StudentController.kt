package dev.leonardpark.kotlinspringbootgradle.controller

import dev.leonardpark.kotlinspringbootgradle.data.entity.Student
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

interface StudentController {
  /**
   * 取得 Student 所有資料
   */
  @GetMapping("/students")
  fun getStudentData(): MutableList<Student>

  /**
   * 新增 Student 資料
   */
  @PostMapping("/students")
  fun addStudentData(@RequestBody student: Student) : Student

  /**
   * 利用姓名查詢學生資料
   */
  @PostMapping("/students/search")
  fun getStudentByName(@RequestParam name: String) : ResponseEntity<List<Student?>>

  /**
   * 修改學生全部資料
   */
  @PutMapping("/students/{id}")
  fun updateStudent(@PathVariable id: Long, @RequestBody student: Student) : ResponseEntity<Student?>

  /**
   * 修改學生信箱（欲更新部份資料）
   */
  @PatchMapping("/students/{id}")
  fun updateStudentEmail(@PathVariable id: Long, @RequestBody student: Student): ResponseEntity<Student?>

  /**
   * 刪除學生資料
   */
  @DeleteMapping("/students/{id}")
  fun deleteStudent(@PathVariable id: Long): ResponseEntity<Any>
}

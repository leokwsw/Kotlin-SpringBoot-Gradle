package dev.leonardpark.kotlinspringbootgradle.data.dao

import dev.leonardpark.kotlinspringbootgradle.data.entity.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface StudentDao : JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {
  fun findByName(name: String): MutableList<Student>
}

package dev.leonardpark.kotlinspringbootgradle.data.entity

import jakarta.persistence.*

@Entity
@Table
data class Student(
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  val id: Int = 0,

  @Column
  val name: String = "",

  @Column
  val email: String = ""
)

package dev.leonardpark.kotlinspringbootgradle.data.dao

import dev.leonardpark.kotlinspringbootgradle.data.entity.Todo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import java.util.*

interface TodoDao : JpaRepository<Todo, Long>, JpaSpecificationExecutor<Todo> {

  /**
   * 查詢符合 Id 條件的資料
   */
  fun findById(id: UUID): Todo?
}

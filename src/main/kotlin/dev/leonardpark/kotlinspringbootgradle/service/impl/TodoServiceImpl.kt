package dev.leonardpark.kotlinspringbootgradle.service.impl

import dev.leonardpark.kotlinspringbootgradle.data.dao.TodoDao
import dev.leonardpark.kotlinspringbootgradle.data.entity.Todo
import dev.leonardpark.kotlinspringbootgradle.service.TodoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class TodoServiceImpl(@Autowired val todoDao: TodoDao) : TodoService {
  override fun getTodos(): Iterable<Todo> = todoDao.findAll()

  override fun createTodo(todo: Todo): Todo = todoDao.save(todo)

  override fun updateTodoStatus(id: String): Boolean = todoDao.findById(UUID.fromString(id)).run {
    return try {
      this?.let {
        if (it.status == 1) it.status = 0 else it.status = 1
        todoDao.save(it)
      }
      true
    } catch (exception: Exception) {
      false
    }
  }

  override fun deleteTodo(id: String): Boolean = todoDao.findById(UUID.fromString(id)).run {
    return try {
      this?.let { todoDao.delete(it) }
      true
    } catch (exception: Exception) {
      false
    }
  }
}

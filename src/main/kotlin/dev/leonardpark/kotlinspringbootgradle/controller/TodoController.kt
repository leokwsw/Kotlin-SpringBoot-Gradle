package dev.leonardpark.kotlinspringbootgradle.controller

import dev.leonardpark.kotlinspringbootgradle.data.entity.Todo
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

interface TodoController {
  @GetMapping("/todos")
  fun getTodos(model: Model): String

  @PostMapping("/todos")
  fun createTodo(@ModelAttribute todo: Todo): String

  @PutMapping("/todos/{id}")
  @ResponseBody
  fun updateTodoStatus(@PathVariable id: String)

  @DeleteMapping("/todos/{id}")
  @ResponseBody
  fun deleteTodo(@PathVariable id: String)
}

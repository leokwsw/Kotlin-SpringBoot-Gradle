package dev.leonardpark.kotlinspringbootgradle.controller.impl

import dev.leonardpark.kotlinspringbootgradle.controller.TodoController
import dev.leonardpark.kotlinspringbootgradle.data.entity.Todo
import dev.leonardpark.kotlinspringbootgradle.service.TodoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model

@Controller
class TodoControllerImpl(@Autowired val todoService: TodoService) : TodoController {
  override fun getTodos(model: Model): String {
    model.addAttribute("todolist", todoService.getTodos());
    model.addAttribute("todoObject", Todo())
    return "todo"
  }

  override fun createTodo(todo: Todo): String {
    todoService.createTodo(todo)

    return "redirect:/todos"
  }

  override fun updateTodoStatus(id: String) {
    todoService.updateTodoStatus(id)
  }

  override fun deleteTodo(id: String) {
    todoService.deleteTodo(id)
  }
}

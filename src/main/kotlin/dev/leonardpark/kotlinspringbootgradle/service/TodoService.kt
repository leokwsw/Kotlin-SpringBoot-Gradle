package dev.leonardpark.kotlinspringbootgradle.service

import dev.leonardpark.kotlinspringbootgradle.data.entity.Todo

interface TodoService {

  /**
   * 取得所有 Todo 資料
   */
  fun getTodos(): Iterable<Todo>

  /**
   * 建立 Todo 資料
   */
  fun createTodo(todo: Todo): Todo

  /**
   * 更新 Todo 狀態
   */
  fun updateTodoStatus(id: String): Boolean

  /**
   * 刪除 Todo 資料
   */
  fun deleteTodo(id: String): Boolean
}

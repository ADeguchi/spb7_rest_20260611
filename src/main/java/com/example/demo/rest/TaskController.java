package com.example.demo.rest;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin //別サーバー(クロスオリジン)からのアクセス許可
@RestController
@RequestMapping("/api1")
public class TaskController {
	private final TaskRepository taskRepository;
	
	//コンストラクタ
	public TaskController (TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}
	
	//テストAPI
	@Operation(summary = "タスクのテスト")
	@GetMapping("/")
	public Task test() {
		Task task = new Task();
		task.setId((long)1);
		task.setName("タスクのサンプル");
		task.setCompleted(false);
		return task;
	}
	
	//全件検索GETメソッド("/view")API
	@Operation(summary = "タスクの全件検索")
	@GetMapping("/view")
	public List<Task> getMethod() {
		return taskRepository.findAll();
	}
	
	//登録POSTメソッド("/create")API
	@Operation(summary = "タスクの登録")
	@PostMapping("/create")
	public void postMethod(@Valid @RequestBody Task task) {
		taskRepository.insertDb(task);
	}
	
	//削除DELETEメソッド("/del/{id}")API
	@Operation(summary = "タスクの削除")
	@DeleteMapping("/del/{id}")
	public void deleteMethod(@PathVariable Long id) {
		taskRepository.deleteDb(id);
	}
	
	//変更PUTメソッド用1件取得("/view/{id}")API
	@Operation(summary = "タスクの1件取得")
	@GetMapping("/view/{id}")
	public Task getOneMethod(@PathVariable Long id) {
		return taskRepository.findById(id);
	}
	
}

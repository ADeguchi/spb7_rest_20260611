package com.example.demo.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TaskRepository {
	//jdbcTemplate
	private final JdbcTemplate jdbcTemplate;
	
	//コンストラクタ
	public TaskRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate =jdbcTemplate;
	}
	
	//GETメソッド用データ全件検索("/view")
	public List<Task> findAll() {
		//検索用SQL
		String sql = "SELECT id, name, completed FROM task ORDER BY id";
		
		//検索処理
		List<Map<String, Object>> resultDb1 = jdbcTemplate.queryForList(sql);
		
		//検索結果保持用
		List<Task> resultDb2 = new ArrayList<Task>();
		
		//検索結果ピックアップ
		for(Map<String, Object> result: resultDb1) {
			Task task = new Task();
			task.setId((Long)result.get("id"));
			task.setName((String)result.get("name"));
			task.setCompleted((Boolean)result.get("completed"));
			
			//resultDb2に結果を保存
			resultDb2.add(task);
		}
		System.out.println("全件検索");
		//結果の返却
		return resultDb2;
	}
	
	//POSTメソッド用データ1件登録
	public void insertDb(Task task) {
		//登録SQL
		String sql = "INSERT INTO task (name, completed) VALUES (?, ?)";
		//登録処理実行
		jdbcTemplate.update(sql, task.getName(), task.getCompleted());
		System.out.println("Name: " + task.getName() + " を登録");
	}
	
	//DELETEメソッド用データ1件削除
	public void deleteDb(Long id) {
		//削除SQL
		String sql = "DELETE FROM task WHERE id = ?";
		//削除処理実行
		jdbcTemplate.update(sql, id);
		System.out.println("ID: " + id + " を削除");
	}
	
	//PUTメソッド用データ1件取得
	public Task findById(Long id) {
		//1件検索用SQL
		String sql = "SELECT id, name, completed FROM task WHERE id = ?";
		//検索処理(1件しかないのでListではなくMapを使用)
		Map<String, Object> result = jdbcTemplate.queryForMap(sql, id);
		//1件検索結果のセット
		Task task = new Task();
		task.setId((Long)result.get("id"));
		task.setName((String)result.get("name"));
		task.setCompleted((Boolean)result.get("completed"));
		
		return task;
	}
	
	//PUTメソッド用データ1件変更
	public void updateDb(Long id, Task task) {
		//変更用SQL
		String sql = "UPDATE task SET name = ?, completed = ? WHERE id = ?";
		//変更処理
		jdbcTemplate.update(sql, task.getName(), task.getCompleted(), id);
		System.out.println("ID: " + id + " を変更");
	}
	
}

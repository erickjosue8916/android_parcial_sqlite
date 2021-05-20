package com.example.parcial3.tasks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.parcial3.database.Database;

import java.util.ArrayList;

public class TaskService {
    SQLiteDatabase connection;
    Context context;

    public TaskService(Context context) {
        this.context = context;
        Database db = new Database(context, "dbUsuario", null, 1);
        this.connection = db.getWritableDatabase();
    }

    public ArrayList<Task> getTasks(String statusFilter) {
        ArrayList tasks = new ArrayList<Task>();

        String sql = "SELECT * FROM tasks WHERE status = '" + statusFilter + "'";

        Cursor query = this.connection.rawQuery(sql, null);
        while (query.moveToNext()){
            int id = query.getInt(query.getColumnIndex("id"));
            String title = query.getString(query.getColumnIndex("title"));
            String description = query.getString(query.getColumnIndex("description"));
            String status = query.getString(query.getColumnIndex("status"));
            tasks.add(new Task(id, title, description, status));
        }
        return tasks;
    }

    public Task get(int idFilter) {

        String sql = "SELECT * FROM tasks WHERE id = " + idFilter + "";
        Task task = new Task();
        Cursor query = this.connection.rawQuery(sql, null);
        while (query.moveToNext()){
            task.id = query.getInt(query.getColumnIndex("id"));
            task.title = query.getString(query.getColumnIndex("title"));
            task.description = query.getString(query.getColumnIndex("description"));
            task.status = query.getString(query.getColumnIndex("status"));
        }
        return task;
    }

    public void update(Task task) {
        ContentValues insertContent = new ContentValues();
        insertContent.put("title", task.title);
        insertContent.put("description", task.description);
        insertContent.put("status", task.status);
        String id = String.valueOf(task.id);
        connection.update("tasks", insertContent, "id = ?", new String[] {id});
        connection.close();
    }

    public void save(Task task) {
        ContentValues insertContent = new ContentValues();
        insertContent.put("title", task.title);
        insertContent.put("description", task.description);
        insertContent.put("status", task.status);
        connection.insert("tasks", null, insertContent);
        connection.close();
    }
}

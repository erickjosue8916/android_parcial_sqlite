package com.example.parcial3.user;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.parcial3.database.Database;

import java.util.ArrayList;

public class UserService {
    SQLiteDatabase connection;
    Context context;

    public UserService(Context context) {
        this.context = context;
        Database db = new Database(context, "dbUsuario", null, 1);
        this.connection = db.getWritableDatabase();
    }

    public ArrayList<User> getUsers() {
        ArrayList users = new ArrayList<User>();
        Cursor query = this.connection.rawQuery("SELECT * FROM users", null);
        while (query.moveToNext()){
            int id= query.getInt(query.getColumnIndex("id"));
            String name = query.getString(query.getColumnIndex("name"));
            String email = query.getString(query.getColumnIndex("email"));
            int age= query.getInt(query.getColumnIndex("age"));
            users.add(new User(id, name, email, age));
        }
        return users;
    }

    public void saveUser(User user) {
        Database base = new Database(context, "dbUsuario",null,1);
        SQLiteDatabase con = base.getWritableDatabase();
        ContentValues insertContent = new ContentValues();
        insertContent.put("name", user.name);
        insertContent.put("email", user.email);
        insertContent.put("age", user.age);
        con.insert("users", null, insertContent);
        con.close();
    }
}

package com.example.claire.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by claire on 11/01/2017.
 */

public class TaskDataSource {

    private SQLiteDatabase database;
    private MySQLiteHelper dbhelper;
    private String[] allColumns = {MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_NAME,
    MySQLiteHelper.COLUMN_CONTENT, MySQLiteHelper.COLUMN_DATE, MySQLiteHelper.COLUMN_CHECK};
    private String[] name = {MySQLiteHelper.COLUMN_NAME};
    private String[] date = {MySQLiteHelper.COLUMN_DATE};
    private String [] check = {MySQLiteHelper.COLUMN_CHECK};


    public TaskDataSource(Context context) {
        dbhelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbhelper.getWritableDatabase();
    }

    public void close() {
        dbhelper.close();
    }

    public TaskModel createTaskModel(String taskName, String taskContent, String taskDate, int check) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_NAME, taskName);
        values.put(MySQLiteHelper.COLUMN_CONTENT, taskContent);
        values.put(MySQLiteHelper.COLUMN_DATE, taskDate);
        values.put(MySQLiteHelper.COLUMN_CHECK, check);

        long insertId = database.insert(MySQLiteHelper.TABLE_TASKS, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_TASKS,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        TaskModel newTaskModel = cursorToTaskModel(cursor);
        cursor.close();

        return newTaskModel;
    }

    public void deleteTask(TaskModel comment) {
        long id = comment.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_TASKS, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<TaskModel> getAllTask() {
        List<TaskModel> tasks = new ArrayList<TaskModel>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_TASKS, allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            TaskModel task = cursorToTaskModel(cursor);
            tasks.add(task);
            cursor.moveToNext();
        }
        cursor.close();
        return tasks;
    }

    public List<String> getAllNames() {
        List<String> names = new ArrayList<String>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_TASKS, name, null, null, null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            String str = cursor.getString(cursor.getColumnIndex("name"));
            names.add(str);
            cursor.moveToNext();
        }
        cursor.close();
        return names;
    }

    public void queryUpdate(String name, String content, String date) {
        ContentValues data = new ContentValues();
        data.put(MySQLiteHelper.COLUMN_CONTENT, content);
        data.put(MySQLiteHelper.COLUMN_DATE, date);

        database.update(MySQLiteHelper.TABLE_TASKS, data, "name = ?", new String[]{name});
    }

    public void updateCheck(String name, int check) {
        ContentValues data = new ContentValues();
        data.put(MySQLiteHelper.COLUMN_CHECK, check);

        database.update(MySQLiteHelper.TABLE_TASKS, data, "name =  ?", new String[]{name});
    }

    public List<String> getAllDates() {
        List<String> dates = new ArrayList<String>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_TASKS, date, null, null, null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            String str = cursor.getString(cursor.getColumnIndex("date"));
            dates.add(str);
            cursor.moveToNext();
        }
        cursor.close();
        return dates;
    }

    private TaskModel cursorToTaskModel(Cursor cursor) {
        TaskModel task = new TaskModel();

        task.setId(cursor.getLong(0));
        task.setName(cursor.getString(1));
        task.setContent(cursor.getString(2));
        task.setDate(cursor.getString(3));
        if (cursor.getInt(4) == 0)
            task.setCheck(false);
        else
            task.setCheck(true);
        return task;
    }

}

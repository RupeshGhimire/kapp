package com.kimbutech.kapp;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseAccess{
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;
    Context context;

    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
        context=context;
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public String databaseToString(int key, int a) {
        String data = "";
        String s = "";
        Cursor c = database.rawQuery("SELECT * FROM kuinfo", null);
        if (a == 2) {
            c.moveToFirst();
            int id = c.getInt(0);
            while (id != key) {
                c.moveToNext();
                id++;
            }
            data = c.getString(2);
            return data;
        } else if (a == 1) {
            c.moveToFirst();
            int id = c.getInt(0);
            while (id != key) {
                c.moveToNext();
                id++;
            }
            data = c.getString(1);
            return data;
        } else if (a == 6) {
            c.moveToFirst();
            int id = c.getInt(0);
            while (id != key) {
                c.moveToNext();
                id++;
            }
            data = c.getString(6);
            return data;
        } else if (a == 3) {
            c.moveToFirst();
            int id = c.getInt(0);
            while (id != key) {
                c.moveToNext();
                id++;
            }
            data = c.getString(3);
            return data;
        } else if (a == 4) {
            c.moveToFirst();
            int id = c.getInt(0);
            while (id != key) {
                c.moveToNext();
                id++;
            }
            data = c.getString(4);
            return data;
        } else if (a == 5) {
            c.moveToFirst();
            int id = c.getInt(0);
            while (id != key) {
                c.moveToNext();
                id++;
            }
            data = c.getString(5);
            return data;
        } else
            return s;
    }
    public String[] getdata()
    {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(null);
        databaseAccess.open();
        String descript[];
        descript = new String[34];
        for(int i=1;i<=34;i++) {
            descript[i] = databaseAccess.databaseToString(i, 5);
        }
        return descript;
    }
    public int[] search(String data) {
        String descript[];
        descript = new String[34];
        int i=0;
        int index[];
        index = new int[34];
        Cursor c = database.rawQuery("SELECT * FROM kuinfo", null);
        for (String s : descript) {
            if (s.compareToIgnoreCase(data) == 0) {
                index[i]=c.getInt(0);
                i++;
            }
        }
        return index;
    }
    public double dataTodouble(int key, int a) {
        double d = 0;
        Cursor c = database.rawQuery("SELECT * FROM kuinfo", null);
        if (a == 3) {
            c.moveToFirst();
            int id = c.getInt(0);
            while (id != key) {
                c.moveToNext();
                id++;
            }
            d = c.getDouble(3);
            return d;
        }
        if (a == 4) {
            c.moveToFirst();
            int id = c.getInt(0);
            while (id != key) {
                c.moveToNext();
                id++;
            }
            d = c.getDouble(4);
            return d;
        } else
            return 0;
    }
    public ArrayList<String> searchrecord(String data)
    {
        ArrayList<String> arraylist = new ArrayList<String>();
        SQLiteDatabase database = openHelper.getReadableDatabase();
        Cursor c = database.rawQuery("SELECT * FROM kuinfo WHERE topdest LIKE '%"+data+"%' OR depname LIKE '%"+data+"%'",null);
        while(c.moveToNext()) {
            arraylist.add(c.getString(1));
        }
        c.close();
        return arraylist;
    }
    public int noofsearch(String data)
    {
        String query = "SELECT * FROM kuinfo WHERE topdest LIKE " + data ;
        SQLiteDatabase database = openHelper.getWritableDatabase();
        Cursor c = database.rawQuery("SELECT * FROM kuinfo WHERE topdest LIKE '%"+data+"%' OR depname LIKE '%"+data+"%'",null);
        int x = c.getCount();
        c.close();
        return x;
    }


}

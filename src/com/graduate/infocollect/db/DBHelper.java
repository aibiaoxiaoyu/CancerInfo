package com.graduate.infocollect.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.graduate.infocollect.InfoCollectApplication;
import com.graduate.infocollect.entity.Contact;
import com.graduate.infocollect.entity.MedicalData;

public class DBHelper extends SQLiteOpenHelper {
	
	private final static String DATABASE_NAME = "db_canerInfoCollection";
	private final static int DATABASE_VERSION = 4;
	private static DBHelper dbHelper;
	
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// 创建数据库
		// TODO Auto-generated method stub
		String sql = "Create table " + InfoProvider.TABLE_CONTACT + "(" + InfoProvider.CONTACT_ID + " integer primary key autoincrement, "
				+ InfoProvider.CONTACT_NAME + " text, " + InfoProvider.CONTACT_IS_DRINK + " text, " + InfoProvider.CONTACT_IS_SMOKE + " text, "
				+ InfoProvider.CONTACT_SEX + " text, " + InfoProvider.CONTACT_IS_CT_NORMAL + " text, " + InfoProvider.CONTACT_HISTORY + " text, "
				+ InfoProvider.CONTACT_BIRTHDAY + " text );";
		db.execSQL(sql);
		sql = "Create table " + InfoProvider.TABLE_MEDICALDATA + "(" + InfoProvider.MEDICALDATA_ID + " integer primary key autoincrement, "
				+ InfoProvider.MEDICALDATA_PSA + " text, " + InfoProvider.MEDICALDATA_CA + " text, " + InfoProvider.MEDICALDATA_AFP + " text );";
		db.execSQL(sql);
		
		sql = "Create table " + InfoProvider.TABLE_NOTIFY + "(" + InfoProvider.NOTIFY_ID + " integer primary key autoincrement, "
				+ InfoProvider.NOTIFY_CONTACTID + " text, " + InfoProvider.NOTIFY_VISTTIME + " text );";
		db.execSQL(sql);
		
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		// 升级数据库
		String sql = " DROP TABLE IF EXISTS " + InfoProvider.TABLE_CONTACT;
		db.execSQL(sql);
		sql = " DROP TABLE IF EXISTS " + InfoProvider.TABLE_MEDICALDATA;
		db.execSQL(sql);
		sql = " DROP TABLE IF EXISTS " + InfoProvider.TABLE_NOTIFY;
		db.execSQL(sql);
		onCreate(db);
	}
	
	public static DBHelper getInstance() {
		if(dbHelper == null)
			dbHelper = new DBHelper(InfoCollectApplication.getInstatce());
		return dbHelper;
	}
	
	public long insert(String tableName, ContentValues cv) {
		SQLiteDatabase db = this.getWritableDatabase();
		long row = db.insert(tableName, null, cv);
		return row;
	}
	
	public List<Contact> getContactList() {
		SQLiteDatabase db = this.getWritableDatabase();
		List<Contact> mList = new ArrayList<Contact>();
		Cursor cursor = db.query(InfoProvider.TABLE_CONTACT, null, null, null, null, null, null);
		if(cursor != null) {
			while(cursor.moveToNext()) {
				Contact c = new Contact();
				c.setName(cursor.getString(cursor.getColumnIndex(InfoProvider.CONTACT_NAME)));
				c.setId(cursor.getString(cursor.getColumnIndex(InfoProvider.CONTACT_ID)));
				c.setSex(cursor.getInt(cursor.getColumnIndex(InfoProvider.CONTACT_SEX)));
				c.setBirthday(cursor.getString(cursor.getColumnIndex(InfoProvider.CONTACT_BIRTHDAY)));
				c.setCtNormal(cursor.getInt(cursor.getColumnIndex(InfoProvider.CONTACT_IS_CT_NORMAL)) == 1 ? true : false);
				c.setSmork(cursor.getInt(cursor.getColumnIndex(InfoProvider.CONTACT_IS_SMOKE)) == 1 ? true : false);
				c.setDrink(cursor.getInt(cursor.getColumnIndex(InfoProvider.CONTACT_IS_DRINK)) == 1 ? true : false);
				mList.add(c);
			}
			cursor.close();
		}
		return mList;
	}
	
	public List<MedicalData> getMedicalList(String contactID) {
		SQLiteDatabase db = this.getWritableDatabase();
		List<MedicalData> mList = new ArrayList<MedicalData>();
		Cursor cursor = db.query(InfoProvider.TABLE_MEDICALDATA, null, InfoProvider.MEDICALDATA_ID + "=?", new String[] {contactID }, null, null,
				null);
		if(cursor != null) {
			while(cursor.moveToNext()) {
				MedicalData m = new MedicalData(contactID);
				m.setAFP((cursor.getString(cursor.getColumnIndex(InfoProvider.MEDICALDATA_AFP))));
				m.setCA((cursor.getString(cursor.getColumnIndex(InfoProvider.MEDICALDATA_CA))));
				m.setPSA((cursor.getString(cursor.getColumnIndex(InfoProvider.MEDICALDATA_PSA))));
				mList.add(m);
			}
			cursor.close();
		}
		return mList;
	}
}

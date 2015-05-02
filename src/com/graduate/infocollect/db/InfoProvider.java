package com.graduate.infocollect.db;

public class InfoProvider {
	public final static String TABLE_CONTACT = "contact";
	public final static String CONTACT_ID = "_id";
	public final static String CONTACT_NAME = "_name";
	public final static String CONTACT_SEX = "_sex";
	public final static String CONTACT_BIRTHDAY = "_birthday";
	public final static String CONTACT_IS_SMOKE = "_is_smoke";
	public final static String CONTACT_IS_DRINK = "_is_drink";
	public final static String CONTACT_HISTORY = "_history";
	public final static String CONTACT_IS_CT_NORMAL = "_is_ct_normal";

	
	public final static String TABLE_MEDICALDATA = "MedicalDataact";
	public final static String MEDICALDATA_ID = "_id";
	public final static String MEDICALDATA_PSA = "_psa";
	public final static String MEDICALDATA_CA = "_ca";
	public final static String MEDICALDATA_AFP = "_afp";
	
	public final static String TABLE_NOTIFY = "notify";
	public final static String NOTIFY_ID = "_id";
	public final static String NOTIFY_CONTACTID = "_contactId";
	public final static String NOTIFY_VISTTIME = "_visttime";
}

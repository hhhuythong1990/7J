package com.sevenj.web.common;

public class ValueConst {

	
	public final static String PRIOPRITY_CODE_INPUT = "prioprityCodeInput";
	public final static String BLOCK_OF_TIME_INPUT = "blockOfTimeInput";
	
	/** Table: [admisellaneous] **/
	// column Type
	public final static int TYPE_GENERAL = 0;
	public final static int TYPE_PLANTYPE = 1;
	public final static int TYPE_PRIOPRITY_CODE = 2;
	public final static int TYPE_NOOVERSEADEST = 4;
	public final static int TYPE_BLOCK_OF_TIME = 7;
	//column TypeName
	public final static String TYPENAME_GENERAL = "General";
	public final static String TYPENAME_PLANTYPE = "PlanType";
	public final static String TYPENAME_PRIOPRITY_CODE = "PrioprityCode";
	public final static String TYPENAME_NOOVERSEADEST = "NoOverseaDest";
	public final static String TYPENAME_BLOCK_OF_TIME = "BlockOfTime";

	// column InheritFromGeneral
	public final static int INHERIT_FROM_GENERAL = 1;
	public final static int NONE_INHERIT_FROM_GENERAL = 0;
}

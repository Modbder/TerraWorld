package terraWorld.terraArts.Utils;

public enum EnumOverlay {
	
	UNKNOWN("unknown",-1),
	SURFACE_GENERIC("surfaceWooden",0),
	UNDERGROUND_GENERIC("undergroundGeneric",1),
	SURFACE_DESERT("surfaceDesert",2),
	SURFACE_JUNGLE("surfaceJungle",3),
	UNDERGROUND_JUNGLE("undergroundJungle",4),
	SURFACE_SWAMP("surfaceSwamp",5),
	UNDERGROUND_COLD("undergroundCold",6),
	HELL("hell",7),
	END("end",8),
	ELDRITCH("labirynth",9)
	
	;

	EnumOverlay(String textureName, int tid)
	{
		id = tid;
		tName = textureName;
	}
	
	String tName;
	int id;
	
	public static String getTextureNameByID(int id)
	{
		for(int i = 0; i < values().length; ++i)
		{
			EnumOverlay enu = values()[i];
			if(enu.id == id)
				return enu.tName;
		}
		
		return "";
	}
	
	public int getID()
	{
		return id;
	}
	
	public String getName()
	{
		return tName;
	}
}

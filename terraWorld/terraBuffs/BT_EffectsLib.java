package terraWorld.terraBuffs;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

public class BT_EffectsLib {
	public static Random rand = new Random(4255467434637L);
	
	public static Hashtable<String,BT_Effect> effects = new Hashtable();
	
	public static List<BT_Effect> effects_list = new ArrayList();
	
	public static List<BT_Effect> tools_effects_list = new ArrayList();
	
	public static List<BT_Effect> armor_effects_list = new ArrayList();
	
	public static BT_Effect getRandomEffect()
	{
		return effects_list.get(rand.nextInt(effects_list.size()));
	}
	
	public static BT_Effect getRandomEffect(int type)
	{
		return effects_list.get(rand.nextInt(effects_list.size()));
	}

}

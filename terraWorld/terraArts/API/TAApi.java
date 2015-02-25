package terraWorld.terraArts.API;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import net.minecraft.item.Item;

public class TAApi {
	public static List<String> itemsNamesByID = new ArrayList();
	public static Hashtable<String,Item> itemsByNames = new Hashtable();
	public static Hashtable<String,Integer> artRarityTable = new Hashtable<String,Integer>();
	public static List<Item>[] rarityLists = new ArrayList[5];

}

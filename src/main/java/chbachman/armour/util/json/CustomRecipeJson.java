package chbachman.armour.util.json;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import chbachman.api.upgrade.IUpgrade;
import chbachman.armour.crafting.Recipe;

import com.google.gson.*;

public class CustomRecipeJson implements JsonDeserializer<Recipe>, JsonSerializer<Recipe>{

	@Override
	public JsonElement serialize(Recipe src, Type typeOfSrc, JsonSerializationContext context){
		JsonObject json = new JsonObject();
		
		json.add("output", context.serialize(src.getRecipeOutput(), IUpgrade.class));
		
		for(int i = 0; i < 3; i++){
			
			JsonArray array = new JsonArray();
			
			for(int x = 0; x < 3; x++){
				Object toSerialize = src.getInput()[i * 3 + x];
				
				if(toSerialize instanceof String){
					array.add(new JsonPrimitive((String) toSerialize));
				}else if(toSerialize instanceof ItemStack){
					array.add(context.serialize((ItemStack) toSerialize, ItemStack.class));
				}
				
				if(toSerialize == null){
					array.add(JsonNull.INSTANCE);
				}
			}

			json.add("row" + (i + 1), array);
			
		}
		
		return json;
	}

	@Override
	public Recipe deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context) throws JsonParseException{
		JsonObject json = element.getAsJsonObject();
		
		IUpgrade output = context.deserialize(json.get("output"), IUpgrade.class);
		
		List<Object> list = new ArrayList<Object>();
		
		JsonArray array = new JsonArray();
		
		array.addAll(json.get("row1").getAsJsonArray());
		array.addAll(json.get("row2").getAsJsonArray());
		array.addAll(json.get("row3").getAsJsonArray());
		
		for(JsonElement e : array){
			
			if(e.isJsonNull()){
				list.add(null);
				continue;
			}
			
			if(!e.isJsonObject()){
				list.add(e.getAsString());
				continue;
			}
			
			list.add((ItemStack) context.deserialize(e, ItemStack.class));
		}
		
		return new Recipe(output, list.toArray(), false);
		
	}

}
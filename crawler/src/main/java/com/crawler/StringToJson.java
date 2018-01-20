package com.crawler;

import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 将String转为List<JSONOBject>
 * @author Dream
 *
 */
public class StringToJson {
	//List<JSONObject> list;
	public JSONArray stringToJson(String html) {
		JSONArray jsonArray=JSONArray.fromObject(html);
//		for(int i=0;i<jsonArray.size();i++) {
//			JSONObject jsonObject=(JSONObject) jsonArray.get(i);
//			Iterator it=obj.keys();
//			while(it.hasNext()) {
//				String key=it.next().toString();
//				System.out.print(""+key);
//				System.out.println(":"+obj.get(key));
//			}
			//list.add(jsonObject);
	//	}
		return jsonArray;
	}

}

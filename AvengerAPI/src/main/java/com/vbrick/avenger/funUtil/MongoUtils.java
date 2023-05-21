/*package com.vbrick.avenger.funUtil;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.vbrick.avenger.setup.Setup;




public class MongoUtils implements Setup{
	private static Logger logger = Logger.getLogger(MongoUtils.class);

	private MongoClient mongo = null;
	private DB db =null;
	private DBCollection table=null;
	private BasicDBObject searchQuery=null;
	
	
	public MongoUtils(String dbString) {
		try {
			mongo = new MongoClient(dbString "qa-u1204-mongo-31a.lab.vb.loc" , 27017);
			 db = mongo.getDB("rev");
			boolean auth = db.authenticate(MONGOUSERNAME, MONGOPASSWORD.toCharArray());
			searchQuery = new BasicDBObject();
		} catch (UnknownHostException exception) {
			logger.info("UnknowHost");
			exception.printStackTrace();
		}
	}
	
	public DBCursor queryMongo(Map<String, String> queryValue,String tableName)
	{
		table = db.getCollection(tableName);//"Media.Video"
        for (Entry<String, String> entry : queryValue.entrySet()) {
        	{
        		searchQuery.put(entry.getKey(), entry.getValue());
        	}
	}
        return table.find(searchQuery);
	}
	public static void main(String[] args) {
		
		MongoUtils mongoUtils = new MongoUtils("qa-u1204-mongo-31a.lab.vb.loc");
		Map<String, String> queryValue = new HashMap<String, String>();
		
		queryValue.put("ApprovalProcessName","PRXVyN");
		queryValue.put("ApproverName","griduser179 griduser179");
		queryValue.put("ApprovalStatus","Approved");
		
		DBCursor cursor = mongoUtils.queryMongo(queryValue, "Media.Video");
		System.out.println(cursor.size());
	}
}











	
	public static void main(String[] args) throws UnknownHostException {
		List<String> dbs = mongo.getDatabaseNames();
		for(String db1 : dbs){
			System.out.println(db1);
		}
		
		table = db.getCollection("Media.Video");
	//	table.findOne("ApprovalStatus","Rejected");
	//	searchQuery.put("ApprovalProcessId" ,"PRXVyN" );
		searchQuery.put("ApprovalProcessName","PRXVyN");
		searchQuery.put("ApproverName","griduser179 griduser179");
		searchQuery.put("ApprovalStatus","Approved");
	//	searchQuery.get("Date/time of approval ");
		

		
		
		DBCursor cursor = table.find(searchQuery);
		System.out.println("cursor size"+cursor.size());
		
		while (cursor.hasNext()) {
			System.out.println("----"+cursor.next());
		}
		for(String coll : tables){
			System.out.println(coll);
		}
	}

}*/
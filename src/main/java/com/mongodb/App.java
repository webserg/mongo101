package com.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;

import java.net.UnknownHostException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws UnknownHostException {
        System.out.println("Hello World!");
        MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", 27017));
        MongoDatabase db = mongoClient.getDatabase("test");
//        DBCollection dbCollection = db.getCollection("grades");
        MongoCollection<Document> dbCollection = db.getCollection("people", Document.class);
//        System.out.println(dbCollection.find());
        Document document = new Document().append("name", "Alice").append("age",30).append("profession","programmer");
//        String str = document.getString("str");
        dbCollection.insertOne(document);
    }
}

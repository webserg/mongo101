package com.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.m101j.util.Helpers;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.descending;

/**
 * Hello world!
 *
 *  db.grades.find({},{'student_id':1, 'type':1, 'score':1, '_id':0}).sort({'student_id':1, 'score':1, }).limit(5)
 *
 * db.grades.aggregate({'$group':{'_id':'$student_id', 'average':{$avg:'$score'}}}, {'$sort':{'average':-1}}, {'$limit':1})
 */
public class HW23 {
    public static void main(String[] args) throws UnknownHostException {
        System.out.println("remove the grade of type \"homework\" with the lowest score for each student from the dataset " +
                "that you imported in the previous homework. Since each document is one grade, it should remove one document per student.!");
        MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", 27017));
        MongoDatabase db = mongoClient.getDatabase("students");
        MongoCollection<Document> dbCollection = db.getCollection("grades", Document.class);
        System.out.println(dbCollection.count());
//        System.out.println(dbCollection.find().sort(new Document("_id", "$student_id")));
//        List<Document> all = dbCollection.find().into(new ArrayList<>());


        Bson filter = eq("type", "homework");
        Bson sort = descending("student_id", "score");
        List<Document> all = dbCollection.find(filter)
                .sort(sort)
                .into(new ArrayList<>());
        Map<Integer, Document> lastIds = new HashMap<>();
        for (Document cur : all) {
            Helpers.printJson(cur);
//            System.out.println(cur.get("student_id"));
            lastIds.put((Integer) cur.get("student_id"), cur);
        }
//        lastIds.forEach((student_id, doc) -> dbCollection.deleteOne(Filters.eq("_id", doc.get("_id"))));
//        AggregateIterable<Document> iterable = dbCollection.aggregate(Arrays.asList(
//                new Document("$group", new Document("_id", "$student_id").append("count", new Document("$sum", 1)))));
//        iterable.forEach(new Block<Document>() {
//            @Override
//            public void apply(final Document document) {
//                System.out.println(document.toJson());
//            }
//        });
//
//        AggregateIterable<Document> iterable2 = dbCollection.mapReduce(aggregate(Arrays.asList(
//                new Document("$group", new Document("_id", "$student_id").append("count", new Document("$sum", 1)))));
//        iterable.forEach(new Block<Document>() {
//            @Override
//            public void apply(final Document document) {
//                System.out.println(document.toJson());
//            }
//        });

//        Document document = new Document().append("name", "Alice").append("age", 30).append("profession", "programmer");
//        String str = document.getString("str");
//        dbCollection.insertOne(document);
    }
}

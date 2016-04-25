/*
 * Copyright 2015 MongoDB, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mongodb.m101j.crud;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.m101j.util.Helpers.printJson;

public class FindWithFilterTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("test");
        MongoCollection<Document> collection = database.getCollection("people");

//        collection.drop();

        // insert 10 documents with two random integers
//        for (int i = 0; i < 10; i++) {
//            collection.insertOne(new Document()
//                                 .append("x", new Random().nextInt(2))
//                                 .append("y", new Random().nextInt(100)));
//        }

//        Bson filter = new Document("x", 0)
//        .append("y", new Document("$gt", 10).append("$lt", 90));

        Bson filter = and(eq("name", "Jones"), gt("age", 30), lt("age", 90));

        List<Document> all = collection.find(filter).into(new ArrayList<>());

        for (Document cur : all) {
            printJson(cur);
        }

        long count = collection.count(filter);
        System.out.println();
        System.out.println(count);
    }
}

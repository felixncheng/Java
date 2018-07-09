package com.chengmboy.datasource;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;

/**
 * @author cheng_mboy
 */
public class Mongo {

    public static void main(String[] args) {
        final String uriString = "mongodb://root:123456@localhost:27017/?authSource=admin";
        MongoClientURI uri = new MongoClientURI(uriString);
        MongoClient mongoClient = new MongoClient(uri);

        MongoIterable<String> strings = mongoClient.listDatabaseNames();
        Block<String> printBlock1 = System.out::println;
        strings.forEach(printBlock1);


        MongoDatabase mongoDB = mongoClient.getDatabase("test");


        MongoCollection<Document> collection = mongoDB.getCollection("inventory");
        FindIterable<Document> findIterable = collection.find(new Document());
        Block<Document> printBlock = document -> System.out.println(document.toJson());
        findIterable.forEach(printBlock);

    }
}

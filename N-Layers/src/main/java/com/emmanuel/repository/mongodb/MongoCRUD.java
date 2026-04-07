package com.emmanuel.repository.mongodb;

import org.bson.Document;

import com.emmanuel.model.Customer;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import org.json.*;


public class MongoCRUD {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public MongoCRUD() {

            String jsonText = "";
            JSONObject obj = null;
            try {
                jsonText = new String(Files.readAllBytes(Paths.get("config.json")), StandardCharsets.UTF_8);
                obj = new JSONObject(jsonText);
            } catch (java.io.IOException e) {
                throw new RuntimeException("Failed reading config.json", e);
            }

            this.mongoClient = MongoClients.create(obj.getJSONObject("db").getString("mongoUri"));
            this.database = mongoClient.getDatabase(obj.getJSONObject("db").getString("database"));
            this.collection = database.getCollection(obj.getJSONObject("db").getString("collection"));
    }

    public void insertCustomer(Customer customer) {
        Document newCustomer = new Document("id", customer.getId())
                .append("first_name", customer.getFirstName())
                .append("last_name", customer.getLastName())
                .append("age", customer.getAge())
                .append("email", customer.getEmail());
        collection.insertOne(newCustomer);
        System.out.println("MongoDB: Inserted " + customer.getFirstName());
    }

    public void readCustomers() {
        System.out.println("\nCurrent Customers in MongoDB:");
        for (Document customer : collection.find()) {
            System.out.println(customer.toJson());
        }
    }

    public void updateCustomer(String oldFirstName, String newFirstName) {
        Document updatedCustomer = new Document("$set", new Document("first_name", newFirstName));
        collection.updateOne(new Document("first_name", oldFirstName), updatedCustomer);
        System.out.println("MongoDB: Updated " + oldFirstName + " to " + newFirstName);
    }

    public void deleteCustomer(String firstName) {
        collection.deleteOne(new Document("first_name", firstName));
        System.out.println("MongoDB: Deleted customer " + firstName);
    }

    public void close() {
        if (mongoClient != null) mongoClient.close();
    }
}
                                
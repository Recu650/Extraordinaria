/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejemplo_1;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 *
 * @author usuario
 */
public class Main
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        try(MongoClient cliente = MongoClients.create("mongodb://root:root@localhost:27017"))
        {
            MongoDatabase db = cliente.getDatabase("geografia");
            MongoCollection<Document> ccaa = db.getCollection("ccaa");
            
            MongoCursor<Document> cursor = ccaa.find().iterator();
            while(cursor.hasNext()){
                Document doc = cursor.next();
                System.out.println(doc.getString("nombre") + " - " + doc.getString("abreviatura"));
            }
        }
    }
    
}

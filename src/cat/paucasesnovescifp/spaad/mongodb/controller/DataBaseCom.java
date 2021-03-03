package cat.paucasesnovescifp.spaad.mongodb.controller;

import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Sorts.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

public class DataBaseCom
{
    MongoClient mongoClient;
    MongoDatabase db;
    MongoCollection<Document> col;

    //Exercici_1
    public DataBaseCom(String ruta, String dbNom, String colNom)
    {
        mongoClient = MongoClients.create(ruta);
        db = mongoClient.getDatabase(dbNom);
        col = db.getCollection(colNom);
    }

    public void insertaDocument(Document doc)
    {
        col.insertOne(doc);
    }

    public ArrayList<Document> tornaTotsllibres()
    {
        ArrayList<Document> torna = new ArrayList<>();

        MongoCursor<Document> cursor = col.find().iterator();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                torna.add(doc);
            }
        } finally {
            cursor.close();
        }
        return torna;
    }

    public ArrayList<Document> tornaLlibresAutor(String autor)
    {
        ArrayList<Document> torna = new ArrayList<>();
        return col.find(new Document("autors.nomAut", autor)).into(torna);
    }

    public ArrayList<Document> tornaLlibresSenseAutor(String autor, String autor2)
    {
        ArrayList<Document> torna = new ArrayList<>();
        FindIterable<Document> troba = col.find(new Document().append("autors.nomAut", new Document()
                .append("$ne", autor).append("$ne", autor2)
        ));

        MongoCursor<Document> cursor = troba.iterator();
        try
        {
            while (cursor.hasNext())
            {
                Document doc = cursor.next();
                torna.add(doc);
            }
        } finally {
            cursor.close();
        }
        return torna;
    }

    public ArrayList<Document> tornaLlibresAmbAutor(String autor, String autor2)
    {
        ArrayList<Document> torna = new ArrayList<>();
        FindIterable<Document> troba = col.find(new Document().append("autors.nomAut", new Document()
                .append("$eq", autor)
        ));
        FindIterable<Document> troba2 = col.find(new Document().append("autors.nomAut", new Document()
                .append("$eq", autor2)
        ));

        MongoCursor<Document> cursor = troba.iterator();
        MongoCursor<Document> cursor2 = troba2.iterator();
        try
        {
            while (cursor.hasNext())
            {
                Document doc = cursor.next();
                torna.add(doc);
            }
            while (cursor2.hasNext())
            {
                Document doc2 = cursor2.next();
                torna.add(doc2);
            }
        } finally {
            cursor.close();
            cursor2.close();
        }
        return torna;
    }

    public ArrayList<Document> tornaTitolDepart()
    {
        ArrayList<Document> torna = new ArrayList<>();
        MongoCursor<Document> cursor = col.find()
                .projection(new Document("_id",0).append("titol",1).append("departament",1))
                .sort(orderBy(ascending("titol"),descending("titol")))
                .iterator();
        try
        {
            while (cursor.hasNext())
            {
                Document doc = cursor.next();
                torna.add(doc);
            }
        } finally {
            cursor.close();
        }
        return torna;
    }

    public ArrayList<Document> tornaTitolIsbn()
    {
        ArrayList<Document> torna = new ArrayList<>();
        MongoCursor<Document> cursor = col.find().projection(fields(include("titol","isbn"),excludeId())).iterator();
        try
        {
            while (cursor.hasNext())
            {
                Document doc = cursor.next();
                torna.add(doc);
            }
        } finally {
            cursor.close();
        }
        return torna;
    }

    public ArrayList<Document> tornaTitolIsbnAggregate()
    {
        ArrayList<Document> torna = new ArrayList<>();
        col.aggregate(Arrays.asList(project(fields(
                include("titol","isbn","autors"))),
                sort(ascending("titol"))))
                .into(torna);
        return torna;
    }

    //Aquest anava a ser el 10 però no he sabut fer una nova coleccio a Java.
    public ArrayList<Document> tornaTitolIsbnAggregateOut()
    {
        ArrayList<Document> torna = new ArrayList<>();
        col.aggregate(Arrays.asList(project(fields(
                include("titol","isbn","autors"))),
                sort(ascending("titol"))))
                .into(torna);
        return torna;
    }

    public void modificaDepartament()
    {
        col.updateMany(eq("departament","Català"),set("departament", "Francès"));
    }

    public void borraDoc(String titol)
    {
        col.deleteOne(eq("titol",titol));
    }

    public void borraDoc(int id)
    {
        col.deleteOne(eq("_id", id));
    }
}

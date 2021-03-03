package cat.paucasesnovescifp.spaad.homework;

import cat.paucasesnovescifp.spaad.mongodb.controller.DataBaseCom;
import cat.paucasesnovescifp.spaad.mongodb.data.Llibre;
import org.bson.Document;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

public class MainMongoDB
{

    public static void main(String[] args)
    {
        //Exercici_1
        DataBaseCom conexio = new DataBaseCom("mongodb://localhost:27017", "DataBase", "Llibres");

        //Exercici_2
        Llibre llibreMap = new Llibre("Modernia", "99-123-567", "Ciencia-Ficcio");
        Document docMap = new Document(llibreMap.toMap());
        conexio.insertaDocument(docMap);

        //Exercici_3
        ArrayList<Document> totsLlibres = conexio.tornaTotsllibres();
        for (Document d: totsLlibres) {
           System.out.println(d.toJson());
        }

        //Exercici_4
        ArrayList<Document> llibresAut = conexio.tornaLlibresAutor("ALVAREZ BLANCA");
        for (Document d: llibresAut) {
            System.out.println(d.toJson());
        }

        //Exercici_5
        ArrayList<Document> llibresSenseAut = conexio.tornaLlibresSenseAutor("VERNETTA, XAVIER", "LLULL, RAMON");
        for (Document d: llibresSenseAut) {
            System.out.println(d.toJson());
        }

        //Exercici_6
        ArrayList<Document> llibresAmbAut = conexio.tornaLlibresAmbAutor("VERNETTA, XAVIER", "LLULL, RAMON");
        for (Document d: llibresAmbAut) {
            System.out.println(d.toJson());
        }

        //Exercici_7
        ArrayList<Document> titolsDepartaments = conexio.tornaTitolDepart();
        for (Document d: titolsDepartaments) {
            System.out.println(d.toJson());
        }

        //Exercici_8
        ArrayList<Document> titolsIsbn = conexio.tornaTitolIsbn();
        for (Document d: titolsIsbn) {
            System.out.println(d.toJson());
        }

        //Exercici_9
        ArrayList<Document> aggregate1 = conexio.tornaTitolIsbnAggregate();
        for (Document d: aggregate1) {
            System.out.println(d.toJson());
        }

        //Exercici_10

        //Exercici_11
        conexio.modificaDepartament();

        //Exercici_12
        conexio.borraDoc("Modernia");
    }
}

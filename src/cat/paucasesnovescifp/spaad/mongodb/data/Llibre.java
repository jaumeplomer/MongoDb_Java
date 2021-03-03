package cat.paucasesnovescifp.spaad.mongodb.data;

import java.util.HashMap;

public class Llibre
{
    String titol, isbn, departament;

    public Llibre(String titol, String isbn, String departament)
    {
        this.titol = titol;
        this.isbn = isbn;
        this.departament = departament;
    }

    public HashMap<String, Object> toMap()
    {
        HashMap<String, Object> torna = new HashMap<>();
        torna.put("titol",this.getTitol());
        torna.put("isbn", this.getIsbn());
        torna.put("departament", this.getDepartament());
        return torna;
    }

    @Override
    public String toString() {
        return "Llibre{" +
                "titol='" + titol + '\'' +
                ", isbn='" + isbn + '\'' +
                ", departament='" + departament + '\'' +
                '}';
    }

    public String getTitol()
    {
        return titol;
    }

    public void setTitol(String titol)
    {
        this.titol = titol;
    }

    public String getIsbn()
    {
        return isbn;
    }

    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }

    public String getDepartament()
    {
        return departament;
    }

    public void setDepartament(String departament)
    {
        this.departament = departament;
    }
}

package barros.jeferson.oceanbook;

import java.util.ArrayList;

/**
 * Created by aluno on 17/10/2016.
 */
public class Item {

    public String categorias;
    public ArrayList<Book> livros;

    public String getCategorias() {
        return categorias;
    }

    public void setCategorias(String categorias) {
        this.categorias = categorias;
    }

    public ArrayList<Book> getLivros() {
        return livros;
    }

    public void setLivros(ArrayList<Book> livros) {
        this.livros = livros;
    }
}

package barros.jeferson.oceanbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.concurrent.RunnableFuture;

public class MainActivity extends AppCompatActivity {
    //private ArrayList<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Book> lista = iniciarLista();

        for (Book book : lista) {
            Log.d("Livro", "Título: " + book.getTitulo());
        }


        //Cria o adapter
        MyAdapter adapter = new MyAdapter(this, lista);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.lista_recyclerview);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Lógica do progress bar
        hideLoad(lista);

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpRequest.GET("http://gitlab.oceanmanaus.com/snippets/1/raw");
            }
        }).start();
    }

    private void hideLoad(ArrayList<Book> lista) {
        if (lista.size() >0 ){
            ProgressBar progressBar = (ProgressBar) findViewById(R.id.loading);
            progressBar.setVisibility(View.GONE);
        }
    }

    public ArrayList<Book> iniciarLista() {
        ArrayList<Book> lista = new ArrayList<>();

        Book book1 = new Book();
        book1.setTitulo("MOODLE 2 para Autores e Tutores - 3ª Edição");
        book1.setAutor("Robson Santos da Silva");
        book1.setCapa("http://172.25.1.17/oceanbook/moodle2.jpg");
        book1.setAno(2016);
        book1.setPaginas(60);

        Book book2 = new Book();
        book2.setTitulo("NoSQL Essencial");
        book2.setAutor("Pramod J. Sadalage / Martin Fowler");
        book2.setCapa("http://172.25.1.17/oceanbook/NoSQLEssencial.png");
        book2.setAno(2013);
        book2.setPaginas(216);

        Book book3 = new Book();
        book3.setTitulo("Jovem e Bem-Sucedido");
        book3.setAutor("Juliano Niederaue");
        book3.setCapa("http://172.25.1.17/oceanbook/JovemeBem-Sucedido.jpg");
        book3.setAno(2013);
        book3.setPaginas(192);

        Book book4 = new Book();
        book4.setTitulo("Lidando com a Incerteza");
        book4.setAutor("Jonathan Fields");
        book4.setCapa("http://172.25.1.17/oceanbook/LidandocomaIncerteza.png");
        book4.setAno(2013);
        book4.setPaginas(208);

        Book book5 = new Book();
        book5.setTitulo("Equipes de Software");
        book5.setAutor("Brian W. Fitzpatrick / Ben Collins-Sussman");
        book5.setCapa("http://172.25.1.17/oceanbook/EquipesdeSoftware.jpg");
        book5.setAno(2012);
        book5.setPaginas(208);

        lista.add(book1);
        lista.add(book2);
        lista.add(book3);
        lista.add(book4);
        lista.add(book5);

        return lista;
    }
}

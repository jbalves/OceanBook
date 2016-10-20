package barros.jeferson.oceanbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.oceanbrasil.libocean.Ocean;
import com.oceanbrasil.libocean.control.glide.GlideRequest;
import com.oceanbrasil.libocean.control.http.Request;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Jeferson Barros on 10/17/16.
 */

public class DetalhesActivity extends AppCompatActivity implements Request.RequestListener{

    private ArrayList<Book> mlista = new ArrayList<>();

    private TextView tituloTextView;
    private TextView autorTextView;
    private TextView paginasTextView;
    private TextView anoTextView;
    private ImageView capaImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhes_activity);

        // Informacoes do livro passado via Extra
        Book book = recuperarLivro();

        // Recuperando views do layout para acessar o layout
        recuperarViews();

        // Setando valores do livro para o layout
        setBook(book);

        Ocean.newRequest("http://gitlab.oceanmanaus.com/snippets/1/raw", this).get().send();
    }

    /**
     * Setando para o layout as informacoes do livro
     * @param book
     */
    private void setBook(Book book) {
        tituloTextView.setText(book.getTitulo());
        autorTextView.setText(book.getAutor());
        paginasTextView.setText(String.valueOf(book.getPaginas()));
        anoTextView.setText(String.valueOf(book.getAno()));
        Ocean.glide(this).load(book.getCapa()).build(GlideRequest.BITMAP).resize(200,200).into(capaImageView);
    }

    /**
     * Recuperando as referencias do layout
     */
    private void recuperarViews() {
        // Recuperando as views do layout
        tituloTextView = (TextView) findViewById(R.id.detalhesTitulo);
        autorTextView = (TextView) findViewById(R.id.detalhesAutor);
        paginasTextView = (TextView) findViewById(R.id.detalhesPaginas);
        anoTextView = (TextView) findViewById(R.id.detalhesAno);
        capaImageView = (ImageView) findViewById(R.id.detalhesImageView);
    }

    /**
     * Recuperando todas as informacoes do livro
     * @return Book
     */
    private Book recuperarLivro() {
        Book book = new Book();
        book = (Book) getIntent().getSerializableExtra("book");
        return book;
    }

    @Override
    public void onRequestOk(String resposta, JSONObject jsonObject, int code) {
        if (code == Request.NENHUM_ERROR) {
            Log.d("Detalhes", "resposta: " + resposta);
            mlista = stringToGson(resposta);
            criarAdapter(mlista);
        }
    }

    private ArrayList<Book> stringToGson(String resposta) {

        ArrayList<Book> livros = new ArrayList<>();
        Gson gson = new Gson();
        Bookstore bookstore = gson.fromJson(resposta, Bookstore.class);
        ArrayList<Item> itens = bookstore.getOcean();

        for (Item item : itens) {
            livros.addAll(item.getLivros());
        }
        return livros;
    }

    public void criarAdapter(ArrayList<Book> lista){

        DetalhesAdapter adapter = new DetalhesAdapter(this,lista);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.detalhes_recyclerview);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }
}

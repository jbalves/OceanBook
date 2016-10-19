package barros.jeferson.oceanbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.oceanbrasil.libocean.Ocean;
import com.oceanbrasil.libocean.control.glide.GlideRequest;

/**
 * Created by Jeferson Barros on 10/17/16.
 */

public class DetalhesActivity extends AppCompatActivity{

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

}

package barros.jeferson.oceanbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.oceanbrasil.libocean.Ocean;
import com.oceanbrasil.libocean.control.glide.GlideRequest;

/**
 * Created by jbalves on 10/17/16.
 */

public class DetalhesActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhes_activity);

        String capa = getIntent().getStringExtra("capa");
        String titulo = getIntent().getStringExtra("titulo");
        String autor = getIntent().getStringExtra("autor");
        String paginas = getIntent().getStringExtra("paginas");
        String ano = getIntent().getStringExtra("ano");

        ImageView capaImageView = (ImageView) findViewById(R.id.detalhesImageView);
        TextView tituloTextView = (TextView) findViewById(R.id.detalhesTitulo);
        TextView autorTextView = (TextView) findViewById(R.id.detalhesAutor);
        TextView paginasTextView = (TextView) findViewById(R.id.detalhesPaginas);
        TextView anoTextView = (TextView) findViewById(R.id.detalhesAno);

        if (capa != null) {
            //processar a imagem
            Ocean
                    .glide(this)
                    .load(capa)
                    .build(GlideRequest.BITMAP)
                    .resize(200,200)
                    .into(capaImageView);
        }

        tituloTextView.setText(titulo);
        autorTextView.setText(autor);
        paginasTextView.setText(paginas);
        anoTextView.setText(ano);
    }

}

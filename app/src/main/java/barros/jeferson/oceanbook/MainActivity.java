package barros.jeferson.oceanbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.oceanbrasil.libocean.Ocean;
import com.oceanbrasil.libocean.control.http.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Request.RequestListener, MyAdapter.AdapterListener {

    private ArrayList<Book> mlista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Ocean.newRequest("http://gitlab.oceanmanaus.com/snippets/1/raw", this).get().send();
    }

    private void hideLoad(ArrayList<Book> lista) {
        if (lista.size() >0 ){
            ProgressBar progressBar = (ProgressBar) findViewById(R.id.loading);
            progressBar.setVisibility(View.GONE);
        }
    }


    @Override
    public void onRequestOk(String resposta, JSONObject jsonObject, int code) {
        if (code == Request.NENHUM_ERROR) {
            Log.d("Debug", "resposta: " + resposta);

            mlista = stringToGson(resposta);

            criarAdapter(mlista);
        }
    }

    private ArrayList<Book> stringToGson (String resposta) {

        ArrayList<Book> livros = new ArrayList<>();

        Gson gson = new Gson();

        Bookstore bookstore = gson.fromJson(resposta, Bookstore.class);

        ArrayList<Item> itens = bookstore.getOcean();

        for (Item item : itens) {
//            Log.d("Categoria", item.getCategorias());
            //Log.d("Livros", item.getLivros().size()+ "");
            livros.addAll(item.getLivros());
        }

        return livros;
    }

    public void stringToJson (String resposta) {

        ArrayList<Book> lista = new ArrayList<>();

        if (resposta !=null) {
            try {
                JSONObject object = new JSONObject(resposta);

                JSONArray ocean = object.getJSONArray("ocean");

                for (int i = 0; i < ocean.length(); i++) {
                    JSONObject item = ocean.getJSONObject(i);

                    JSONArray livros = item.getJSONArray("livros");
                    for (int j=0; j<livros.length();j++){

                        JSONObject livro = livros.getJSONObject(j);
                        String titulo = livro.getString("titulo");
                        String autor = livro.getString("autor");
                        int ano = livro.getInt("ano");
                        int paginas = livro.getInt("paginas");
                        String capa = livro.getString("capa");

                        Book book = new Book();
                        book.setTitulo(titulo);
                        book.setAutor(autor);
                        book.setAno(ano);
                        book.setPaginas(paginas);
                        book.setCapa(capa);

                        mlista.add(book);

                        Log.d("Debug",titulo);
                        Log.d("Debug",autor);
                        Log.d("Debug",String.format("%s",ano));
                        Log.d("Debug",String.format("%s",paginas));
                        Log.d("Debug",capa);
                        Log.d("Debug","ID: " +mlista.size());

                    }
                }

                criarAdapter(mlista);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    public void criarAdapter(ArrayList<Book> lista){

        //Cria o adapter
        MyAdapter adapter = new MyAdapter(this, lista);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.lista_recyclerview);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setmAdapterListener(this);

        //Lógica do progress bar
        hideLoad(lista);
    }

    @Override
    public void onItemClick(View view, int position) {

        Book book = mlista.get(position);
        Intent intent = new Intent(MainActivity.this,DetalhesActivity.class);
        intent.putExtra("book",book);
        startActivity(intent);
    }
}

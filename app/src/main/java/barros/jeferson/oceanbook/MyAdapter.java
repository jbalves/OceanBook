package barros.jeferson.oceanbook;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aluno on 10/10/2016.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<Book> lista;

    public MyAdapter (ArrayList<Book> lista) {
        this.lista = lista;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_list, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Recuperei a referência do meu livro
        Book book = lista.get(position);

        //Seta os valores do livro para o layout dentro do holder
        holder
                .setTitulo(book.getTitulo())
                .setAno(book.getAno())
                .setAutor(book.getAutor())
                .setPagina(book.getPaginas())
                .setCapa(book.getCapa());
    }

    @Override
    public int getItemCount() {
        //tamanho da lista
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tituloView;
        private TextView autorView;
        private TextView paginaView;
        private TextView anoView;
        private ImageView capaView;

        public ViewHolder(View itemView) {
            super(itemView);
            //Recuperei as referências do layout
            tituloView = (TextView) itemView.findViewById(R.id.txtTitulo);
            autorView = (TextView) itemView.findViewById(R.id.txtAutor);
            paginaView = (TextView) itemView.findViewById(R.id.txtPaginas);
            anoView = (TextView) itemView.findViewById(R.id.txtAno);
            capaView = (ImageView) itemView.findViewById(R.id.imgCapa);
        }

        public ViewHolder setTitulo(String titulo) {
            if (tituloView == null) return this;
            tituloView.setText(titulo);
            return this;
        }

        public ViewHolder setAutor(String autor) {
            if (autorView == null) return this;
            autorView.setText(autor);
            return this;
        }

        public ViewHolder setPagina(int pagina) {
            if (paginaView == null) return this;
            paginaView.setText(pagina+"");
            return this;
        }

        public ViewHolder setAno(int ano) {
            if (paginaView == null) return this;
            anoView.setText(ano+"");
            return this;
        }

        public ViewHolder setCapa(String capa){
            if (capaView == null) return this;
            //processar a imagem
        }

    }
}

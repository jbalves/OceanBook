package barros.jeferson.oceanbook;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.oceanbrasil.libocean.Ocean;
import com.oceanbrasil.libocean.control.glide.GlideRequest;

import java.util.ArrayList;

/**
 * Created by Jeferson Barros on 19/10/2016.
 */

public class DetalhesAdapter extends RecyclerView.Adapter<DetalhesAdapter.ViewHolder>{

    private final Context mContext;
    private ArrayList<Book> mLista;

    public DetalhesAdapter(Context context, ArrayList<Book> lista) {
        this.mLista = lista;
        this.mContext = context;
    }

    //#2 monta o layout da lista
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detalhes_item_book_list,null);
        return new ViewHolder(view);
    }

    //#3 recupera uma posição da lista no layout
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Recupera a referência do meu livro
        Book book = mLista.get(position);

        //Seta os valores do livro para o layout dentro do holder
        holder.setCapa(book.getCapa());
    }

    //#4 Retorna a quantidade de elementos existente na lista
    @Override
    public int getItemCount() {
        return mLista.size();
    }

    //#1 método a ser implementado
    //mapeia os elementos de layout
    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView capaView;

        public ViewHolder(View itemView) {
            super(itemView);

            //Recuperando as referências do layout
            capaView = (ImageView) itemView.findViewById(R.id.detalhes_item_imageView);
        }

        public ViewHolder setCapa(String capa){
            if (capaView == null) return this;
            //processar a imagem
            Ocean
                    .glide(mContext)
                    .load(capa)
                    .build(GlideRequest.BITMAP)
                    .resize(200,200)
                    .into(capaView);
            return this;
        }
    }
}

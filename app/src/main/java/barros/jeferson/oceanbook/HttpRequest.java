package barros.jeferson.oceanbook;

import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Jeferson Barros on 11/10/2016.
 */

public class HttpRequest {

    public static String GET(String path) {
        String result = "";

        try {

            URL url = new URL(path);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");
            conexao.connect();

            int resposta = conexao.getResponseCode();

            if (resposta == HttpURLConnection.HTTP_OK){
                InputStream is = conexao.getInputStream();
                result = bytesToString(is);
                Log.d("Http response: ",result);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String bytesToString(InputStream is) throws IOException {
        byte[] bufffer = new byte[1024];
        //Irá armazenar todos os bytes lidos
        ByteArrayOutputStream bufferBig= new ByteArrayOutputStream();

        int bytesLidos;
        while ((bytesLidos = is.read(bufffer)) != -1) {
            bufferBig.write(bufffer,0,bytesLidos);
        }

        return new String(bufferBig.toByteArray(),"UTF-8");
    }
}

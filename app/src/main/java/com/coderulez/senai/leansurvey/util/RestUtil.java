package com.coderulez.senai.leansurvey.util;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * Created by SENAI on 12/11/2016.
 */

public class RestUtil {

    /**
     * Método para realizar a leitura de um InputStream e convertê-lo em String
     *
     * @param in InputStream com os dados a serem lidos
     * @return conteúdo convertido em String
     */

   public static String readStream(InputStream in){
       //Cria uma referência para o BufferedReader
       BufferedReader reader = null;
        //Cria um StringReader
       StringBuilder builder = new StringBuilder();


       try {
           //Cria o BufferedReader passando o InputStream recebido como parâmetro
           reader = new BufferedReader(new InputStreamReader(in));
           //cria uma linha nula
           String line = "null";
           //Lê as linhas qe acrescenta ao StringBuilder até que não haja mais conteudo nas linhas
           while ((line = reader.readLine()) != null){
               builder.append(line +"\n");
           }
       }catch(Exception e){
            Log.w("ERRO", e.getMessage());
       }finally {
           if (reader != null){
               try {
                   //tenta fechar o reader
                   reader.close();
               }catch (Exception e2){
                   Log.w("ERRO", e2.getMessage());
               }
           }
       }

    //retorna a String lida
       return builder.toString();

   }


    public static String get(String URL, Context contexto) throws IOException {

        String retorno = null;

        if (isConexaoDisponivel(contexto)){
            //Cria a URL baseado na String com o endereço completo do service
            URL url = new URL(URL);
            HttpURLConnection conexao = null;

            try{
                conexao = (HttpURLConnection) url.openConnection();
                conexao.setRequestMethod("GET");
                conexao.setConnectTimeout(15000);
                conexao.setReadTimeout(15000);
                conexao.connect();

                InputStream in = null;
                int status = conexao.getResponseCode();

                if(status >= HttpURLConnection.HTTP_BAD_REQUEST){
                    Log.e("ERRO", "ERRO AO REALIZAR O GET");
                    //in = conexao.getErrorStream();
                    throw new RuntimeException("Erro: " + status);

                }else {

                    in = conexao.getInputStream();

            }
                retorno = RestUtil.readStream(in);
                Log.w("ESSE", retorno);
                in.close();
        }catch (IOException e){
                e.printStackTrace();
                throw e;
            }finally {
                if (conexao != null){
                    conexao.disconnect();
                }
            }
        }else {
            throw new RuntimeException("Sem conexão");
        }

        return retorno;

    }


    public static String post(String json, String URL, Context contexto) throws Exception{
        String retorno;

        if(isConexaoDisponivel(contexto)){
            //Cria a URL baseado na String com o endereço completo do service

            URL url = new URL(URL);

            HttpURLConnection conexao = null;

            try{
                conexao = (HttpURLConnection) url.openConnection();
                conexao.setRequestProperty("Content-Type", "application/json;charset=utf-8");
                conexao.setRequestMethod("POST");
                conexao.setDoInput(true);
                conexao.setDoOutput(true);
                conexao.connect();

                OutputStream out = conexao.getOutputStream();
                out.write(json.getBytes("UTF-8"));
                out.flush();
                out.close();

                InputStream in = null;
                int status = conexao.getResponseCode();

                if(status >= HttpURLConnection.HTTP_BAD_REQUEST){
                    Log.e("ERRO", "ERRO AO REALIZAR O POST");
                    throw new RuntimeException("ERRO: " + status);

                }else {
                    in = conexao.getInputStream();
                }
                retorno = RestUtil.readStream(in);
                Log.w("RETORNO", retorno);
                in.close();
            }catch (IOException e){
                throw e;

            }finally {
                if (conexao != null){
                    conexao.disconnect();
                }
            }
        }else {
            throw new RuntimeException("SEM CONXEXÂO");
        }

        return retorno;

    }



    public static String put(String json, String URL, Context contexto) throws  Exception{
        String retorno;

        if (isConexaoDisponivel(contexto)){
            //Cria a URL baseado na String com o endereço completo do service

            URL url = new URL(URL);
            HttpURLConnection conexao = null;

            try {
                conexao = (HttpURLConnection) url.openConnection();
                conexao.setRequestProperty("Content-Type", "application/json;charset=utf-8");
                conexao.setRequestMethod("PUT");
                conexao.setDoInput(true);
                conexao.setDoOutput(true);
                conexao.connect();

                OutputStream out = conexao.getOutputStream();
                out.write(json.getBytes("UTF-8"));
                out.flush();
                out.close();

                InputStream in = null;
                int status = conexao.getResponseCode();

                if (status >= HttpURLConnection.HTTP_BAD_REQUEST) {

                    Log.e("ERRO", "ERRO AO REALIZAR O PUT");
                    throw new RuntimeException("ERRO: " + status);

                } else {
                    in = conexao.getInputStream();
                }
                retorno = RestUtil.readStream(in);
                Log.w("RETORNO", retorno);
                in.close();
        }catch (IOException e){
                throw e;
            }finally {
                if (conexao != null){
                    conexao.disconnect();
                }
            }
        }else {
            throw new RuntimeException("SEM CONEXÃO");
        }

        return retorno;

    }

    public static boolean isConexaoDisponivel(Context context){
        //return true

        try {
            ConnectivityManager conectividade = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            if (conectividade == null){
                return false;
            }else{
                NetworkInfo info = conectividade.getActiveNetworkInfo();
                if (info.getState().equals(NetworkInfo.State.CONNECTED)){
                    return true;
                }else {
                    return false;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

}

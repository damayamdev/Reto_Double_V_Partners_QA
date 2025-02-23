package utils;


import io.cucumber.core.internal.com.fasterxml.jackson.databind.JsonNode;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class LeerCorreoTemporal {

    private String correoRemitente;
    private String asunto;

    public LeerCorreoTemporal(String correoRemitente, String asunto) {
        this.asunto = asunto;
        this.correoRemitente = correoRemitente;
    }

    public String getPeticionAsString(String url) {
        String strMensajes = "";
        Request request = new Request.Builder().url(url)
                .get()
                .addHeader("Mailsac-Key", "k_ospIpO8WlDbarzvXx7vcUZ31AfbKOxy1LvXcaqqp1qUb8")
                .build();
        try {
            OkHttpClient cliente = new OkHttpClient();
            Response response = cliente.newCall(request).execute();
            strMensajes = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strMensajes;
    }

    public String buscarMensaje(JsonNode mensajes) {
        String fromAddress;
        String subject;
        String emailId = "";
        for(int i = 0; i < mensajes.size(); i++) {
            fromAddress = getContenido(getNodo(mensajes.get(i),"from").get(0),"address");
            subject = getContenido(mensajes.get(i),"subject");

            if(fromAddress.equals(correoRemitente) || subject.contains(asunto)) {
                emailId = getContenido(mensajes.get(i),"_id");
                break;
            }
        }
        return emailId;
    }

    private String getContenido(JsonNode nodo, String campo) {
        return nodo.get(campo).asText();
    }

    private JsonNode getNodo(JsonNode padre, String nodo) {
        return padre.get(nodo);
    }


}

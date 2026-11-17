package es.etg.dawes.java.rest.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class Cliente {

    public static final String RUTA_EJEMPLO = "https://jsonplaceholder.typicode.com/posts/1";
    public static final String METODO_GET = "GET";
    public static final String METODO_POST = "POST";
    public static final String METODO_PUT = "PUT";
    public static final String METODO_DELETE = "DELETE";

    private static final String MENSAJE_FORMATO = "Código de estado: %s%nCuerpo de la respuesta:%n%s";
    private static final String HEADER_ACCEPT_NAME = "Accept";
    private static final String HEADER_ACCEPT_VALUE = "application/json";
    private static final String HEADER_CONTENT_TYPE_NAME = "Content-Type";
    private static final String HEADER_CONTENT_TYPE_VALUE = "application/json";
    private static final String MENSAJE_ERROR = "Error en la ejecución del servicio";
    private static final String MENSAJE_METODO_NO_SOPORTADO = "Método HTTP no soportado: ";
    private static final int TIMEOUT_SEGUNDOS = 10;

    private final String ruta;

    public Cliente(String ruta) {
        this.ruta = ruta;
    }

    public String ejecutar(String metodo, String cuerpoJson) {
        String salida;
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(TIMEOUT_SEGUNDOS))
                .build();

        try {
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(URI.create(ruta))
                    .header(HEADER_ACCEPT_NAME, HEADER_ACCEPT_VALUE)
                    .header(HEADER_CONTENT_TYPE_NAME, HEADER_CONTENT_TYPE_VALUE);

            switch (metodo) {
                case METODO_GET:
                    requestBuilder.GET();
                    break;
                case METODO_POST:
                    requestBuilder.POST(HttpRequest.BodyPublishers.ofString(cuerpoJson));
                    break;
                case METODO_PUT:
                    requestBuilder.PUT(HttpRequest.BodyPublishers.ofString(cuerpoJson));
                    break;
                case METODO_DELETE:
                    requestBuilder.DELETE();
                    break;
                default:
                    throw new IllegalArgumentException(MENSAJE_METODO_NO_SOPORTADO + metodo);
            }

            HttpRequest request = requestBuilder.build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            salida = String.format(MENSAJE_FORMATO, response.statusCode(), response.body());
        } catch (Exception e) {
            salida = MENSAJE_ERROR;
        }
        return salida;
    }
}
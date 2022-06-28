package br.com.alelo.consumer.consumerpat;

import static io.restassured.RestAssured.given;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import io.restassured.http.ContentType;
import io.vertx.core.json.JsonObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConsumerControllerTest {

  @Test
  public void documentoSubstituicaoResourceTest() {
    Consumer requisicao = new Consumer();

    requisicao.setEmail("hilquiaswpc10@outlook.com");
    String body = montaBody(requisicao);

    given()
      .contentType(ContentType.JSON)
      .body(body)
      .when()
      .post("consumer/createConsumer")
      .then()
      .statusCode(400);
  }

  @Test
  public void enviarDocumentoSubstituicaoIMATest() {
    Consumer requisicao = new Consumer();

    requisicao.setMobilePhoneNumber("+55 81 98879-9043");
    String body = montaBody(requisicao);

    given()
      .contentType(ContentType.JSON)
      .body(body)
      .when()
      .post("consumer/setcardbalance")
      .then()
      .statusCode(400);
  }

  private String montaBody(Consumer requisicao) {
    JsonObject bodyJson = new JsonObject();
    bodyJson.put("drugstoreCardBalance", requisicao.getDrugstoreCardBalance());
    return bodyJson.toString();
  }
}

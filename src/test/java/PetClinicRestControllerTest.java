import com.javaegitimleri.petclinic.model.Owner;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.hibernate.validator.constraints.br.TituloEleitoral;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.runner.RunWith;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("dev")
public class PetClinicRestControllerTest{
    @Autowired
    private TestRestTemplate testRestTemplate;

    //Her test metodu çalışmadan önce çalışarak içerisindeki kodu exacute eder
    @Before
    public void setUp() {
        testRestTemplate = testRestTemplate.withBasicAuth("user2","secret");
    }

    //Test metodları void olur
    @Test
    public void testGetOwnerById() {
        ResponseEntity<Owner> response = testRestTemplate.getForEntity("http://localhost:8080/rest/owner/1", Owner.class);
        MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
        //MatcherAssert.assertThat(response.getBody().getFirstName(), Matchers.equalTo("Kenan"));
    }

    @Test
    public void testGetOwnersByLastName() {
        ResponseEntity<List> response = testRestTemplate.getForEntity("http://localhost:8080/rest/owner?ln=Sevindik", List.class);
        MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
        List<Map<String, String>> body = response.getBody();
        List<String> firstNames = body.stream().map(a->a.get("firstName")).collect(Collectors.toList());
        MatcherAssert.assertThat(firstNames, Matchers.containsInAnyOrder("Kenan", "Hümeyra", "Salim"));
    }

    @Test
    public void  testGetOwnres(){
       ResponseEntity<List> response = testRestTemplate.getForEntity("http://localhost:8080/rest/owners", List.class);
       MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
        List<Map<String, String>> body = response.getBody();
        List<String> firstNames = body.stream().map(a->a.get("firstName")).collect(Collectors.toList());
        MatcherAssert.assertThat(firstNames, Matchers.containsInAnyOrder("Kenan", "Hümeyra", "Salim", "Muammer"));
    }
    @Test
    public void testCreateOwner() {
        Owner owner = new Owner();
        owner.setFirstName("Metehan");
        owner.setLastName("Yücel");

        URI location = testRestTemplate.postForLocation("http://localhost:8080/rest/owner", owner);

        Owner owner2 = testRestTemplate.getForObject(location, Owner.class);

        MatcherAssert.assertThat(owner2.getFirstName(), Matchers.equalTo(owner.getFirstName()));
        MatcherAssert.assertThat(owner2.getLastName(), Matchers.equalTo(owner.getLastName()));
    }
    @Test
    public void testUpdateOwner(){
        Owner owner = testRestTemplate.getForObject("http://localhost:8080/rest/owner/4", Owner.class);
        MatcherAssert.assertThat(owner.getFirstName(), Matchers.equalTo("Salim"));
        owner.setFirstName("Salim Güray");
        testRestTemplate.put("http://localhost:8080/rest/owner/4", owner);
        owner = testRestTemplate.getForObject("http://localhost:8080/rest/owner/4", Owner.class);
        MatcherAssert.assertThat(owner.getFirstName(), Matchers.equalTo("Salim Güray"));

    }
    @Test
    public void testDeleteOwner(){
        //restTemplate.delete("http://localhost:8080/rest/owner/1");
        ResponseEntity<Void> responseEntity = testRestTemplate.exchange("http://localhost:8080/rest/owner/1", HttpMethod.DELETE, null, Void.class);
        try {
            testRestTemplate.getForObject("http://localhost:8080/rest/owner/1", Owner.class);
            Assert.fail("sould have not returned owner");
        } catch (HttpClientErrorException ex){
           MatcherAssert.assertThat(ex.getStatusCode().value(), Matchers.equalTo(404));
        }

    }
    @Test
    public void testServiceLevelValidation(){
        Owner owner = new Owner();
        //owner.setFirstName("K");
       // owner.setLastName("S");
        ResponseEntity<URI> responseEntity = testRestTemplate.postForEntity("http://localhost:8080/rest/owner", owner, URI.class);
        MatcherAssert.assertThat(responseEntity.getStatusCode().value(), Matchers.equalTo(HttpStatus.PRECONDITION_FAILED));

    }
}

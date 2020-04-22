import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.junit.Test;

public class PasswordEncoderTest {
    @Test
    public void generateEncodedPasswords(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("{bcrypt}" + passwordEncoder.encode("secret"));
        System.out.println("{bcrypt}" + passwordEncoder.encode("secret"));
        System.out.println("{bcrypt}" + passwordEncoder.encode("secret"));


    }
}

package defaulttest;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import junit.framework.TestCase;

public class PasswordHash extends TestCase {

	static String pass = "secret";
	
	public static void testMD5Hash() {
		Md5PasswordEncoder passEncode = new Md5PasswordEncoder();
		System.out.println(passEncode.encodePassword(pass, null));
	}
	
	public static void bCryptHash() {
		BCryptPasswordEncoder bcryp = new BCryptPasswordEncoder();
		System.out.println(bcryp.encode(pass));
	}
	
	public static void main(String[] args) {
		testMD5Hash();
		bCryptHash();
	}

}

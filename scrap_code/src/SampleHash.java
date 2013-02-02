import java.math.BigInteger;
import java.security.MessageDigest;
import org.apache.commons.codec.binary.*;
import javax.xml.soap.Text;

public class SampleHash {

	private static final byte[] secretKey = "FDlzKL;A8fdsa)3802!fd!FDSA*#".getBytes();
	
	public static void main(String[] args) throws Exception {
		String username = "jon.armstrong@e-hps.com";
		String msbDistrict = "NK_12240";
		
		byte[] usernameBytes = username.getBytes("UTF-8");
		byte[] msbDistrictBytes = msbDistrict.getBytes("UTF-8"); 
		
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		messageDigest.update(secretKey);
		messageDigest.update(usernameBytes);
		messageDigest.update(msbDistrictBytes);
		
		byte[] digest = messageDigest.digest();

		String hashString = Base64.encodeBase64URLSafeString(digest);
		System.out.println(hashString);
		
	}
	
}

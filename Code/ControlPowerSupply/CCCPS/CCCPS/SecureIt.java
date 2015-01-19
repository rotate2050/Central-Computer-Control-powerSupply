package CCCPS;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class SecureIt {
	private SecretKeySpec sKey;
	private Cipher encryptor, decryptor;
	
	public SecureIt(String key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException{
		sKey = new SecretKeySpec(key.getBytes(), "DES");
		
		encryptor = Cipher.getInstance("DES");
		decryptor = Cipher.getInstance("DES");
		
		encryptor.init(Cipher.ENCRYPT_MODE, sKey);
		decryptor.init(Cipher.DECRYPT_MODE, sKey);
	}
	
	public String encrypt(String plaintext) throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
		byte[] utf8Data = plaintext.getBytes("UTF8");
		//encrypt
		byte[] enc = encryptor.doFinal(utf8Data);
		//encode
		return new BASE64Encoder().encode(enc).toString();
	}
	
	public String decrypt(String ciphertext) throws IOException, IllegalBlockSizeException, BadPaddingException {
		//decode
		byte[] dec = new BASE64Decoder().decodeBuffer(ciphertext);
		//decrypt
		byte[] utf8 = decryptor.doFinal(dec);
		return new String(utf8, "UTF8");
	}
}
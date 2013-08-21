package com.school.userclass;
// 암호화 패키지, 패스워드 암호화만 할 계획 - 0821
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;
@Component(value="EncryptionEncoding")
public class EncryptionEncoding {
	private MessageDigest digest = null;

	/* 필요 라이브러리 
	 *  메이븐에 다음 dependency 추가
	 *   <!-- commons.codec Base64 인코딩/디코딩 -->
        <dependency>
            <groupId>commons-codec</groupId>
            <artifactId>commons-codec</artifactId>
            <version>1.6</version>
        </dependency>
	 * 
	 * */ 
	
	
	
	// SHA-1 Method : input data : String type , return data : String type 38 글자 문자열
	// MD5 Method : input data : String type , return data : String type 32 글자 문자열
	// TripleDES encoding : input data : String type, return data : String 24 글자 문자열
	// TripleDES Decoding : input data : String type, return data : String
	public EncryptionEncoding() {
		// TODO Auto-generated constructor stub
	}

	// SHA-1 Encryption 
	public String SHA1(String password) {
		try {
			// Create MD5 Hash
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.update(password.getBytes());
			byte messageDigest[] = digest.digest();

			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < messageDigest.length; i++)
				hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	// MD5 Encryption

	public String MD5(String password) {
		try {
			digest = MessageDigest.getInstance("MD5");
			byte[] md = digest.digest(password.getBytes());
			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < md.length; i++) {
				hexString.append(Integer.toString((md[i] & 0xf0) >> 4, 16));
				hexString.append(Integer.toString(md[i] & 0x0f, 16));
			}
			return hexString.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Triple DES Encoding and Decoding

	// key값 리턴
	public String key() {
		// 24자리(24바이트)만 key 값으로 입력 가능
		return "ab_booktv_abcd0912345678";
	}

	/**
	 * 지정된 비밀키를 가지고 오는 메서드 (TripleDES) require Key Size : 24 bytes
	 * 
	 * @return
	 * @throws Exception
	 */
	public Key getKey(String keyValue) throws Exception {
		DESedeKeySpec desKeySpec = new DESedeKeySpec(keyValue.getBytes());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
		Key key = keyFactory.generateSecret(desKeySpec);
		return key;
	}

	/**
	 * 문자열 대칭 암호화
	 * 
	 * @param ID
	 *            비밀키 암호화를 희망하는 문자열
	 * @return String 암호화된 ID
	 * @exception Exception
	 */
	public String TripleDesEncoding(String ID) throws Exception {
		if (ID == null || ID.length() == 0)
			return "";

		String instance = (key().length() == 24) ? "DESede/ECB/PKCS5Padding": "DES/ECB/PKCS5Padding";
		javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(instance);
		cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, getKey(key()));
		String amalgam = ID;

		byte[] inputBytes1 = amalgam.getBytes("UTF8");
		byte[] outputBytes1 = cipher.doFinal(inputBytes1);

		Base64.encodeBase64(outputBytes1);

		String outputStr1 = new String(Base64.encodeBase64(outputBytes1));
		return outputStr1;
	}

	/**
	 * 문자열 대칭 복호화
	 * 
	 * @param codedID
	 *            비밀키 복호화를 희망하는 문자열
	 * @return String 복호화된 ID
	 * @exception Exception
	 */
	public String TripleDesDecoding(String codedID) throws Exception {
		if (codedID == null || codedID.length() == 0)
			return "";

		String instance = (key().length() == 24) ? "DESede/ECB/PKCS5Padding": "DES/ECB/PKCS5Padding";
		javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(instance);
		cipher.init(javax.crypto.Cipher.DECRYPT_MODE, getKey(key()));

		byte[] inputBytes1 = Base64.decodeBase64(codedID);
		byte[] outputBytes2 = cipher.doFinal(inputBytes1);

		String strResult = new String(outputBytes2, "UTF8");
		return strResult;
	}
}

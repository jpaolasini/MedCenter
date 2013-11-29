package com.example.medcenter;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESencrp {

  // Specify the key value that will be used for our encryption process
  private static final String ALGO = "AES";
  private static final byte[] keyValue = new byte[] { 'T', 'h', 'e', 'B', 'e',
      's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y' };

  /**
   * Encrypts a byte array using a cryptosystem to secure data.
   * 
   * @param Data
   *          The byte[] to be encrypted.
   * @return The encrypted byte[]
   * @throws Exception
   *           thrown for encryption error.
   */
  public static byte[] encrypt(byte[] Data) throws Exception {
    SecretKeySpec key = generateKey();
    Cipher c = Cipher.getInstance(ALGO);
    c.init(Cipher.ENCRYPT_MODE, key);
    
    byte[] encVal = c.doFinal(Data);
    return encVal;
  }

  /**
   * Decrypts a byte array using a cryptosystem to secure data.
   * 
   * @param encryptedData
   *          The byte[] to be decrypted.
   * @return The decrypted byte[]
   * @throws Exception
   *           thrown for deryption error.
   */
  public static byte[] decrypt(byte[] encryptedData) throws Exception {
    SecretKeySpec key = generateKey();
    Cipher c = Cipher.getInstance(ALGO);
    c.init(Cipher.DECRYPT_MODE, key);

    byte[] decValue = c.doFinal(encryptedData);
    return decValue;
  }

  /**
   * Generates a secretKey based on the key value.
   * 
   * @return The secret key used for specifying encryption.
   */
  private static SecretKeySpec generateKey() throws Exception {
    SecretKeySpec key = new SecretKeySpec(keyValue, ALGO);
    return key;
  }
}

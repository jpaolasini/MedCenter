package com.example.medcenter;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.util.Log;

public class FileHandler extends AESencrp {
  /**
   * Read an encrypted file with a stored byte array and returns it unencrypted
   * 
   * @param fileName
   *          Name of the file being read in
   * @return Unencrypted String
   * @throws IOException
   * @throws FileNotFoundException
   *           Exception thrown if the given file is not found
   */
  public static String ReadFile(String filePath, String fileName)
      throws Exception, IOException, FileNotFoundException {

    File file = new File(filePath + fileName);
    int fileSize = (int) file.length();
    byte[] buffer = new byte[fileSize];
    try {
      BufferedInputStream buf = new BufferedInputStream(new FileInputStream(
          file));
      buf.read(buffer, 0, buffer.length);
      buf.close();

      byte[] decryptedBytes = decrypt(buffer);

      String decryptedString = new String(decryptedBytes);
      return decryptedString;
    } catch (FileNotFoundException fnfe) {
      return "Exception";
    }
  }

  /**
   * Writes a String into an encrypted byte array file If the text file exists,
   * it is overwritten.
   * 
   * @param filePath
   *          Path of the file to write to
   * @param fileName
   *          Name of the file being written to
   * @param fileData
   *          String input
   * @return Returns encrypted byte array
   * @throws Exception
   *           thrown for encryption error
   * @throws IOException
   *           Exception thrown if inventory cannot be written to the file
   */
  public static String WriteFile(String filePath, String fileName,
      String fileData) throws Exception, IOException {
    File file = new File(filePath, fileName);

    try {
      OutputStream os = new FileOutputStream(file);
      byte[] byteInformation = encrypt(fileData.getBytes());
      //byte[] testBytes = fileData.getBytes();
      os.write(byteInformation);
     // os.write(testBytes);
      os.close();
      
      return new String(byteInformation);

    } catch (IOException e) {
      // Unable to create file, likely because external storage is
      // not currently mounted.
      Log.w("ExternalStorage", "Error writing " + file, e);
    }
    return null;
  }

  /**
   * Create a new file on the internal storage so that we can write to it.
   */
  public static File createDirectory(String directoryName, String directoryPath) {
    // Get the directory we wish to save our files to and save our file there.
    File file = new File(directoryPath, directoryName);
    if (!file.mkdirs()) {
    }
    return file;
  }
}

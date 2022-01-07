package training.java.io.utils;

import java.io.*;
import java.net.URL;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class IOUtil {
  public static URL getResource(String res) throws IOException {
    if (res.startsWith("file:")) {
      File file = new File(res.substring("file:".length()));
      return !file.exists() ? null : file.toURI().toURL();
    } else if (res.startsWith("classpath:")) {
      res = res.substring("classpath:".length());
      return Thread.currentThread().getContextClassLoader().getResource(res);
    } else {
      return Thread.currentThread().getContextClassLoader().getResource(res);
    }
  }

  public static boolean hasResource(String res) throws IOException {
    return getResource(res) != null;
  }

  public static URL findURL(String res) throws IOException {
    if (res.startsWith("file:")) {
      return (new File(res.substring("file:".length()))).toURI().toURL();
    } else if (res.startsWith("classpath:")) {
      res = res.substring("classpath:".length());
      return Thread.currentThread().getContextClassLoader().getResource(res);
    } else {
      return Thread.currentThread().getContextClassLoader().getResource(res);
    }
  }

  public static InputStream loadResource(String res) throws IOException {
    URL url = getResource(res);
    return url == null ? null : url.openStream();
  }

  public static String loadResourceAsString(String res) throws IOException {
    InputStream is = loadResource(res);
    return getStreamContentAsString(is, "UTF-8");
  }

  public static String loadResourceAsString(String res, String encoding) throws IOException {
    InputStream is = loadResource(res);
    return getStreamContentAsString(is, encoding);
  }

  public static String getFileContentAsString(File file, String encoding) throws Exception {
    FileInputStream is = new FileInputStream(file);
    return new String(getStreamContentAsBytes(is), encoding);
  }

  public static String getFileContentAsString(File file) throws Exception {
    FileInputStream is = new FileInputStream(file);
    return new String(getStreamContentAsBytes(is));
  }

  public static String getFileContentAsString(String fileName, String encoding) throws Exception {
    if (fileName == null) {
      return null;
    } else {
      FileInputStream is = new FileInputStream(fileName);
      return new String(getStreamContentAsBytes(is), encoding);
    }
  }

  public static String getFileContentAsString(String fileName) throws Exception {
    FileInputStream is = new FileInputStream(fileName);
    String data = new String(getStreamContentAsBytes(is));
    is.close();
    return data;
  }

  public static byte[] getFileContentAsBytes(File file) throws Exception {
    FileInputStream is = new FileInputStream(file);
    byte[] data = getStreamContentAsBytes(is);
    is.close();
    return data;
  }

  public static byte[] getFileContentAsBytes(String fileName) throws Exception {
    FileInputStream is = new FileInputStream(fileName);
    byte[] data = getStreamContentAsBytes(is);
    is.close();
    return data;
  }

  public static String getStreamContentAsString(InputStream is, String encoding) throws IOException {
    return new String(getStreamContentAsBytes(is), encoding);
  }

  public static String getStreamContentAsString(Reader in) throws IOException {
    char[] data = new char[4912];
    StringBuilder b = new StringBuilder();

    int available;
    while((available = in.read(data)) > -1) {
      b.append(data, 0, available);
    }

    return b.toString();
  }

  public static byte[] getStreamContentAsBytes(InputStream is) {
    try {
      BufferedInputStream buffer = new BufferedInputStream(is);
      ByteArrayOutputStream output = new ByteArrayOutputStream();
      byte[] data = new byte[4912];
      boolean var4 = true;

      int available;
      while((available = buffer.read(data)) > -1) {
        output.write(data, 0, available);
      }

      is.close();
      return output.toByteArray();
    } catch (IOException var5) {
      throw new IllegalArgumentException("Cannot convert input stream", var5);
    }
  }

  public static char[] getCharacters(Reader reader) throws IOException {
    CharArrayWriter writer = new CharArrayWriter(4912);
    char[] data = new char[4912];
    boolean var3 = true;

    int available;
    while((available = reader.read(data)) > -1) {
      writer.write(data, 0, available);
    }

    reader.close();
    writer.close();
    return writer.toCharArray();
  }

  public static byte[] getStreamContentAsBytes(InputStream is, int maxRead) throws IOException {
    BufferedInputStream buffer = new BufferedInputStream(is);
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    byte[] data = new byte[4912];
    int available;
    for(int read = 0; (available = buffer.read(data)) > -1 && read < maxRead; read += available) {
      if (maxRead - read < available) {
        available = maxRead - read;
      }

      output.write(data, 0, available);
    }

    is.close();
    return output.toByteArray();
  }

  public static String getResourceAsString(String resource, String encoding) throws Exception {
    ClassLoader cl = Thread.currentThread().getContextClassLoader();
    URL url = cl.getResource(resource);
    InputStream is = url.openStream();
    String data = getStreamContentAsString(is, encoding);
    is.close();
    return data;
  }

  public static byte[] getResourceAsBytes(String resource) throws Exception {
    ClassLoader cl = Thread.currentThread().getContextClassLoader();
    URL url = cl.getResource(resource);
    InputStream is = url.openStream();
    byte[] data = getStreamContentAsBytes(is);
    is.close();
    return data;
  }

  public static void save(String data, String file) throws IOException {
    FileOutputStream os = new FileOutputStream(file);
    byte[] buf = data.getBytes();
    os.write(buf);
    os.close();
  }

  public static void save(String data, String encoding, String file) throws IOException {
    FileOutputStream os = new FileOutputStream(file);
    byte[] buf = data.getBytes(encoding);
    os.write(buf);
    os.close();
  }

  public static void save(File file, byte[] data) throws IOException {
    FileOutputStream os = new FileOutputStream(file);
    os.write(data);
    os.close();
  }

  public static byte[] serialize(Object obj) throws IOException {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    ObjectOutputStream out = new ObjectOutputStream(bytes);
    out.writeObject(obj);
    out.close();
    return bytes.toByteArray();
  }

  public static Object deserialize(byte[] bytes) throws Exception {
    if (bytes == null) {
      return null;
    } else {
      ByteArrayInputStream is = new ByteArrayInputStream(bytes);
      ObjectInputStream in = new ObjectInputStream(is);
      Object obj = in.readObject();
      in.close();
      return obj;
    }
  }

  public static byte[] compress(byte[] data) throws IOException {
    Deflater deflater = new Deflater();
    deflater.setInput(data);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
    deflater.finish();
    byte[] buffer = new byte[1024];

    while(!deflater.finished()) {
      int count = deflater.deflate(buffer);
      outputStream.write(buffer, 0, count);
    }

    outputStream.close();
    return outputStream.toByteArray();
  }

  public static byte[] decompress(byte[] data) throws IOException, DataFormatException {
    Inflater inflater = new Inflater();
    inflater.setInput(data);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
    byte[] buffer = new byte[1024];

    while(!inflater.finished()) {
      int count = inflater.inflate(buffer);
      outputStream.write(buffer, 0, count);
    }

    outputStream.close();
    return outputStream.toByteArray();
  }
}
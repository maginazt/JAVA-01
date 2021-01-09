package cn.maginazt;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CustomClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (!name.equals("Hello")) {
            throw new ClassNotFoundException("cannot find class");
        }
        try {
            byte[] bytes = readHelloBytes();
            convertBytes(bytes);
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("Hello class find error", e);
        }
    }

    private void convertBytes(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (255 - bytes[i]);
        }
    }

    private byte[] readHelloBytes() throws IOException {
        InputStream is = CustomClassLoader.class.getResourceAsStream("Hello.xlass");
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int len = 0;
        while ((len = is.read(buffer, 0, buffer.length)) != -1) {
            bos.write(buffer, 0, len);
        }
        return bos.toByteArray();
    }
}

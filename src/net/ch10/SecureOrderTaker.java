package net.ch10;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Arrays;

public class SecureOrderTaker {
    public final static int PORT = 7000;
    public final static String algorithm = "SSL";
    public static void main(String[] args) {
        try {
            SSLContext context = SSLContext.getInstance(algorithm);
            // Эталонная реализация поддерживает только ключи X.509
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            // Хранилище ключей Oracle по умолчанию
            KeyStore ks = KeyStore.getInstance("JKS");
            // В целях безопасности каждое хранилище ключей зашифровано
            //парольной фразой, которая должна быть предоставлена до того, как мы сможем загрузить
            //это с диска. Ключевая фраза хранится в виде массива char[]
            //таким образом, его можно быстро стереть из памяти, а не
            //ждать сборщика мусора.
            char[] password = System.console().readPassword();
            ks.load(new FileInputStream("jnp4e.keys"), password);
            kmf.init(ks, password);
            context.init(kmf.getKeyManagers(), null, null);
            // сотрите пароль
            Arrays.fill(password, '0');
            SSLServerSocketFactory factory
                    = context.getServerSocketFactory();
            SSLServerSocket server
                    = (SSLServerSocket) factory.createServerSocket(PORT);
            // добавление анонимных (не прошедших проверку подлинности) наборов шифров
            String[] supported = server.getSupportedCipherSuites();
            String[] anonCipherSuitesSupported = new String[supported.length];
            int numAnonCipherSuitesSupported = 0;
            for (int i = 0; i < supported.length; i++) {
                if (supported[i].indexOf("_anon_") > 0) {
                    anonCipherSuitesSupported[numAnonCipherSuitesSupported++] =
                            supported[i];
                }
            }
            String[] oldEnabled = server.getEnabledCipherSuites();
            String[] newEnabled = new String[oldEnabled.length
                    + numAnonCipherSuitesSupported];
            System.arraycopy(oldEnabled, 0, newEnabled, 0, oldEnabled.length);
            System.arraycopy(anonCipherSuitesSupported, 0, newEnabled,
                    oldEnabled.length, numAnonCipherSuitesSupported);
            server.setEnabledCipherSuites(newEnabled);
            // Теперь все настройки завершены, и мы можем сосредоточиться
            // о фактическом сообщении.
            while (true) {
                // Этот сокет будет защищен,
                // но в коде нет никаких указаний на это!
                try (Socket theConnection = server.accept()) {
                    InputStream in = theConnection.getInputStream();
                    int c;
                    while ((c = in.read()) != -1) {
                        System.out.write(c);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (IOException | KeyManagementException
                 | KeyStoreException | NoSuchAlgorithmException
                 | CertificateException | UnrecoverableKeyException ex) {
            ex.printStackTrace();
        }
    }
}

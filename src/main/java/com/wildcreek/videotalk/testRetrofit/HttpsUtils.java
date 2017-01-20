package com.wildcreek.videotalk.testRetrofit;

import okhttp3.*;
import okio.Buffer;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

/**
 * Https工具类
 */
public class HttpsUtils {
    public static class SSLParams {
        public SSLSocketFactory sSLSocketFactory;
        public X509TrustManager trustManager;
    }

    public static SSLParams getSslSocketFactory(InputStream[] certificates, InputStream bksFile, String password) {
        SSLParams sslParams = new SSLParams();
        try {
            TrustManager[] trustManagers = prepareTrustManager(certificates);
            KeyManager[] keyManagers = prepareKeyManager(bksFile, password);
            SSLContext sslContext = SSLContext.getInstance("TLS");
            X509TrustManager trustManager = null;
            if (trustManagers != null) {
                trustManager = new MyTrustManager(chooseTrustManager(trustManagers));
            } else {
                trustManager = new UnSafeTrustManager();
            }
            sslContext.init(keyManagers, new TrustManager[]{trustManager}, null);
            sslParams.sSLSocketFactory = sslContext.getSocketFactory();
            sslParams.trustManager = trustManager;
            return sslParams;
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        } catch (KeyManagementException e) {
            throw new AssertionError(e);
        } catch (KeyStoreException e) {
            throw new AssertionError(e);
        }
    }

    private class UnSafeHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    private static class UnSafeTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }

    private static TrustManager[] prepareTrustManager(InputStream... certificates) {
        if (certificates == null || certificates.length <= 0)
            return null;
        try {

            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;
            for (InputStream certificate : certificates) {
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));
                try {
                    if (certificate != null)
                        certificate.close();
                } catch (IOException e) {
                    System.out.println("HttpUtils.prepareTrustManager 异常：" + e.toString());
                }
            }
            TrustManagerFactory trustManagerFactory = null;

            trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);

            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();

            return trustManagers;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    private static KeyManager[] prepareKeyManager(InputStream bksFile, String password) {
        try {
            if (bksFile == null || password == null)
                return null;

            KeyStore clientKeyStore = KeyStore.getInstance("BKS");
            clientKeyStore.load(bksFile, password.toCharArray());
            KeyManagerFactory keyManagerFactory = KeyManagerFactory
                    .getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(clientKeyStore, password.toCharArray());
            return keyManagerFactory.getKeyManagers();

        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static X509TrustManager chooseTrustManager(TrustManager[] trustManagers) {
        for (TrustManager trustManager : trustManagers) {
            if (trustManager instanceof X509TrustManager) {
                return (X509TrustManager) trustManager;
            }
        }
        return null;
    }

    private static class MyTrustManager implements X509TrustManager {
        private X509TrustManager defaultTrustManager;
        private X509TrustManager localTrustManager;

        public MyTrustManager(X509TrustManager localTrustManager) throws NoSuchAlgorithmException, KeyStoreException {
            TrustManagerFactory var4 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            var4.init((KeyStore) null);
            defaultTrustManager = chooseTrustManager(var4.getTrustManagers());
            this.localTrustManager = localTrustManager;
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            try {
                defaultTrustManager.checkServerTrusted(chain, authType);
            } catch (CertificateException ce) {
                localTrustManager.checkServerTrusted(chain, authType);
            }
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    public static final String ISEE_SERVER_SER = "-----BEGIN CERTIFICATE-----\n"
            + "MIIDdjCCAl6gAwIBAgIEGdcnGzANBgkqhkiG9w0BAQsFADBjMQswCQYDVQQGEwJjbjEQMA4GA1UE"
            + "CBMHYmVpamluZzEQMA4GA1UEBxMHeGljaGVuZzEUMBIGA1UEChMLY2hpbmFtb2JpbGUxDTALBgNV"
            + "BAsTBGNtY2MxCzAJBgNVBAMTAmh1MB4XDTE2MTEzMDAxMTYwM1oXDTQ0MDQxNzAxMTYwM1owYzEL"
            + "MAkGA1UEBhMCY24xEDAOBgNVBAgTB2JlaWppbmcxEDAOBgNVBAcTB3hpY2hlbmcxFDASBgNVBAoT"
            + "C2NoaW5hbW9iaWxlMQ0wCwYDVQQLEwRjbWNjMQswCQYDVQQDEwJodTCCASIwDQYJKoZIhvcNAQEB"
            + "BQADggEPADCCAQoCggEBAJ8fFPTvXU9Ar8zp3+AQBe17bmksv/e9W3RgPA+Oh7HRib1tKqgnx81A"
            + "iyUR00B+8F2/EnST94M7Nq0zXbhYAzRS8i+pN+tkkkAC00yoAzGyUwK1eu03+EXvQT5iS2DHxnWG"
            + "SeilDbBQE3W6vw1LzkAQgAQgjBfzr248bXVttfpJ4vsdNOnJW+2FG3U/62HIq57OW85zslMRbc/9"
            + "GNEIh9slRhq8Lb1PUcGNFHwwN+K4POrmoCBc0drFQ1pneZZykiIemgIyfG/W5ZAMxmsQ1ruoAKjy"
            + "rWg3FvAoZR9rjLRRXntylClkjHlNO7mXMGi01fI/v/H6XzUhhFUUsRQMhUECAwEAAaMyMDAwDwYD"
            + "VR0RBAgwBocEwKgBhTAdBgNVHQ4EFgQU/ChQ05VS9CFWPM6gI0YYestmvkIwDQYJKoZIhvcNAQEL"
            + "BQADggEBAH8OzlaRruTiD8+hs6SKF4mUMc3GBZI5VJOsirP2CtvBoTmkdnQOJuDVu7BHF2nMc8IB"
            + "VFRd9xeTTwdH8iIM52v+KzYy242E+oRoU/JL8ltgUGodNzSE6PDUdUEouIxlYlBNVI9+ES+hSXAY"
            + "ESgPuIlKGXTv1P3HegvMi5CAQ6uVyG82QjYyKvbBVM2M1oKbUMXbj6ArtHacLA26FoB2W81guar8"
            + "3NsNMsamY/leZNTPgbLJOUCWKwrZ/6EHpepRYT4RuSLW5sircDspBVTK3soc/CsL9Twk1koo/+X/"
            + "vAotHM7R4Y5up2G8sjaSKvKZHnh1re85ta9KsUcsOlBjUpM=\n" + "-----END CERTIFICATE-----";
    public static final InputStream[] SERVER_CERTIFICATES = new InputStream[]{new Buffer().writeUtf8(ISEE_SERVER_SER)
            .inputStream()};

    private static class OkHttpEngineHolder {
        public static final HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(SERVER_CERTIFICATES, null, null);
        public static OkHttpClient httpInstance = new OkHttpClient();
        public static OkHttpClient httpsInstance = new OkHttpClient().newBuilder().connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                }).sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .build();

        static {
            httpInstance.newBuilder().connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request();
                            Response response = null;
                            boolean responseOK = false;
                            int tryCount = 0;

                            while (!responseOK && tryCount < 3) {
                                try {
                                    response = chain.proceed(request);
                                    responseOK = response.isSuccessful();
                                } catch (Exception e) {
                                    System.out.println("请求失败，request:" + request.url() + ",重试次数： " + tryCount);
                                } finally {
                                    tryCount++;
                                }
                            }
                            if (response == null) {
                                return new Response.Builder().protocol(Protocol.HTTP_1_1).request(request).code(500).build();
                            } else {
                                return response;
                            }
                        }
                    })
                    .build();

        }
    }

    public static OkHttpClient getOkHttpClient() {
        return OkHttpEngineHolder.httpInstance;
    }

    public static OkHttpClient getOkHttpsClient() {
        return OkHttpEngineHolder.httpsInstance;
    }
}

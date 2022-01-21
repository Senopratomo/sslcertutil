package org.senolab.sslcertutil.utils;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;

public class ServerTLSCertChecker {

    public static void checkServerCert(String protocol, String host, int port) {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance(protocol);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            System.out.println(ServerTLSCertChecker.class.getSimpleName()+" ");
            e.printStackTrace();
        }
        X509TrustManager passthroughTrustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        try {
            sslContext.init(null, new TrustManager[] { passthroughTrustManager }, null);
        } catch (KeyManagementException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        SSLSocketFactory ssf = sslContext.getSocketFactory();
        SSLSocket socket = null;
        try {
            socket = (SSLSocket) ssf.createSocket(host, port);
            System.out.println("Remote IP: " +socket.getInetAddress());
            socket.startHandshake();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        X509Certificate[] peerCertificates = null;
        try {
            peerCertificates = (X509Certificate[]) socket.getSession().getPeerCertificates();
        } catch (SSLPeerUnverifiedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (int i=0; i<peerCertificates.length; i++) {
            switch(i) {
                case 0:
                    System.out.println("Leaf cert:");
                    break;
                case 1:
                    System.out.println("Intermediate cert:");
                    break;
                case 2:
                    System.out.println("Root cert:");
                    break;
                default:
                    System.out.println("Cert: ");
            }
            System.out.println("CN: "+peerCertificates[i].getSubjectX500Principal());
            try {
                System.out.println("Other Subject Alternative Name(s) - aka SAN (including the CN): "+peerCertificates[i].getSubjectAlternativeNames());
            } catch (CertificateParsingException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            System.out.println("First date to use: "+peerCertificates[i].getNotBefore());
            System.out.println("Expiration date: "+peerCertificates[i].getNotAfter());
            try {
                System.out.println("SHA-1 fingerprint "+getFingerPrint(peerCertificates[i]));
            } catch (CertificateEncodingException | NoSuchAlgorithmException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println();
        }
    }

    public static String getFingerPrint(X509Certificate cert) throws NoSuchAlgorithmException, CertificateEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] der = cert.getEncoded();
        md.update(der);
        byte[] digest = md.digest();
        return hexify(digest);
    }

    public static String hexify (byte bytes[]) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        StringBuffer buf = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; ++i) {
            buf.append(hexDigits[(bytes[i] & 0xf0) >> 4]);
            buf.append(hexDigits[bytes[i] & 0x0f]);
        }
        return buf.toString();
    }
}

package org.senolab.sslcertutil.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class CertificateUtil {

    public static X509Certificate getX509Certificate(String encodedString) {
        if(encodedString == null) return null;
        byte[] decodedData = Base64Util.decode(encodedString);
        try (InputStream inputStream = new ByteArrayInputStream(decodedData)) {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            Certificate certificate = cf.generateCertificate(inputStream);
            if(certificate instanceof X509Certificate) {
                return (X509Certificate) certificate;
            }
        } catch (IOException e) {
            System.out.println(CertificateUtil.class.getSimpleName()+": error occurred when reading input stream");
            e.printStackTrace();
        } catch (CertificateException e) {
            System.out.println(CertificateUtil.class.getSimpleName()+": error occurred when parsing the certificate");
            e.printStackTrace();
        }
        return null;
    }

}

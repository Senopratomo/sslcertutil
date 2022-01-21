package org.senolab.sslcertutil;

import org.senolab.sslcertutil.utils.CertificateUtil;
import org.senolab.sslcertutil.utils.InstructionUtil;
import org.senolab.sslcertutil.utils.ServerTLSCertChecker;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;

public class App {
    public static void main(String[] args) {
        if(args.length == 2 || args.length == 4) {
            switch (args[0]) {
                case "parsex509text":
                    X509Certificate cert = CertificateUtil.getX509Certificate(args[1]);
                    try {
                        System.out.println("SAN: "+cert.getSubjectAlternativeNames());
                        System.out.println("Issuer: "+cert.getIssuerX500Principal());
                        System.out.println("Valid from: "+cert.getNotBefore());
                        System.out.println("Valid to: "+cert.getNotAfter());
                        System.out.println("Serial number: "+cert.getSerialNumber());
                        System.out.println("SHA-1 algorithm: "+ ServerTLSCertChecker.getFingerPrint(cert));
                    } catch (CertificateParsingException e) {
                        System.out.println(App.class.getSimpleName()+ ": an issue occurred when extracting" +
                                "information from the cert");
                        e.printStackTrace();
                    } catch (CertificateEncodingException e) {
                        System.out.println(App.class.getSimpleName()+ ": an issue occurred when encoding certificate");
                        e.printStackTrace();
                    } catch (NoSuchAlgorithmException e) {
                        System.out.println(App.class.getSimpleName()+ ": the CLI doesn't understand the algorithm use");
                        e.printStackTrace();
                    }
                    break;
                case "checktlscert":
                    ServerTLSCertChecker.checkServerCert(args[1], args[2], Integer.parseInt(args[3]));
                    break;
                default:
                    System.out.println("You specified arguments that the CLI doesn't recognize. Please see below for the available arguments");
                    System.out.println(InstructionUtil.printInstruction());
                    break;
            }
        } else {
            System.out.println(InstructionUtil.printInstruction());
        }
    }
}

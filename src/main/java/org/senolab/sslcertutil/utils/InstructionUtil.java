package org.senolab.sslcertutil.utils;

public class InstructionUtil {

    public static String printInstruction() {
        return """
        SSLCertUtil v0.1.0
        
        This is simple CLI to parse SSL cert from base64-encoded representation (eg from SAML SP metadata)
        or parse directly from remote server (by specifying IP or host) by connecting in port 443.
        
        The CLI takes either 2 or 4 arguments (depends on the command):
        1. 1st arg - the command.
           current available command:
           - parsex509text
             It parse base64-encoded representation of the cert (the text between -----BEGIN CERTIFICATE-----
             and -----END CERTIFICATE-----)
             You can specify the text in the second argument.
             
           - checktlscert
             It check and parse SSL/TLS cert on the specified hostname (or IP address), port number, and SSL/TLS protocol, 
             then output information about the SSL/TLS certificate.
             You can specify: 
             a) the SSL/TLS protocol in the second argument
             b) the hostname or IP address in third argument
             c) the port number in fourth argument
             
             
        2. 2nd arg - the argument to the command above - see "the command" section above
        
        3. 3rd arg - the argument to "checktlscert" - see above
        
        4. 4th arg - the argument to "checktlscert" - see above
        
        If you encounter any issues, please open "issues" in the Github repository        
        """;

    }
}

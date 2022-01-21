<h1>SSL Cert Util </h1>
This is simple CLI to parse SSL cert from base64-encoded representation (eg from SAML SP metadata)
or parse directly from remote server (by specifying IP or host) by connecting in port 443.

<p>To use it, compile and create .jar (using Maven or IDE)</p>
<p>The CLI takes either 2 or 4 arguments (depends on the command):</p>
<ol>
    <li>
        1st arg - the command.<br>
        current available command:<br>
        <ul>
            <li>parsex509text<br>
            It parse base64-encoded representation of the cert (the text between -----BEGIN CERTIFICATE-----
            and -----END CERTIFICATE-----).<br>
            You can specify the text in the second argument.
            </li>
            <li>checktlscert<br>
            It check and parse SSL/TLS cert on the specified hostname (or IP address), port number, and SSL/TLS protocol,
            then output information about the SSL/TLS certificate.
            <ul>You can specify:
            <li> the SSL/TLS protocol in the second argument</li>
            <li> the hostname or IP address in third argument</li>
            <li> the port number in fourth argument</li>    
            </ul>
            </li>
        </ul>
    </li>
    <li>2nd arg - the argument to the command above - see "the command" section above
    </li>
    <li>3rd arg - the argument to "checktlscert" - see above
    </li>
    <li>4th arg - the argument to "checktlscert" - see above
    </li>
</ol>


<p>Command line + argument description:<br>
$sslcertutil <--command --> <--command options--> <--command options--> <--command options--></p>

<p>Example:<br>
Sample with 2 arguments:<br>
$sslcertutil parsex509text "MIIGUjCCBTqgAwIBAgIQDQC/L7...."<br>
<br>
Sample with 4 arguments:<br>
$sslcertutil checktlscert TLSv1.2 www.senolab.org 443<br>
<br>

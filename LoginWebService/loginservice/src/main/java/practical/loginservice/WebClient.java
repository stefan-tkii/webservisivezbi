package practical.loginservice;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WebClient {

    private static final String endpoint = "http://localhost:8080/loginservice";

    public static void main(String[] args) {
        new WebClient().testLogin("stefan", "feit123"); //za testiranje na logiranje, ovoj akaunt go ima uste od start vo inicijalizacija
        //new WebClient().testRegister("zoran", "zoko"); za testiranje na registracija, nov akaunt koj go nema od start
        //new WebClient().testUnregister("stefan", "feit123", "feit123"); za testiranje na brisenje na akaunt
    }

    private void testRegister(String username, String password, String confirmpass) {
        String requrl = endpoint + "/loginservlet" + "?username=" + username + "&" + "password=" + password + "&"
                + "confirmpassword=" + confirmpass + "&" + "register=Register";
        try 
        {
            URL url = new URL(requrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.connect();
            readResponse("Response from register request:\n", conn);
            conn.disconnect();
        } 
        catch (MalformedURLException e) 
        {  e.printStackTrace(); }
        catch (IOException e) 
        { e.printStackTrace(); }

    }

    private void testLogin(String username, String password)
    {
        String requrl = endpoint + "/loginservlet" + "?usernamelogin=" + username + "&" + "passwordlogin=" + password + "&"
        + "login=Login";
        try 
        {
            URL url = new URL(requrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.connect();
            readResponse("Response from login request:\n", conn);
            conn.disconnect();
        } 
        catch (Exception e) 
        { e.printStackTrace(); }
    }

    private void testUnregister(String username, String password, String confirmpassword)
    {
        String requrl = endpoint + "/loginservlet" + "?username=" + username + "&" + "password=" + password + "&"
        + "confirmpassword=" + confirmpassword;
        try
        {
            URL url = new URL(requrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("DELETE");
            conn.connect();
            readResponse("Response from unregister request:\n", conn);
            conn.disconnect();
        }
        catch(Exception e)
        { e.printStackTrace(); }
    }

    private void readResponse(String string, HttpURLConnection conn) 
    {
        try
        {
            byte[ ] buffer = new byte[4096];
            InputStream in = conn.getInputStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int n = 0;
            int code = conn.getResponseCode();
            System.out.println("Response code is: " + code + "\n");
            while ((n = in.read(buffer)) != -1) out.write(buffer, 0, n);
            in.close();
            System.out.println(new String(out.toByteArray()));
        }
        catch(Exception e) { throw new RuntimeException(e); }
    }

}
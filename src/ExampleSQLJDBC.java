import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class ExampleSQLJDBC {

    public static void main(String[] args) throws ClassNotFoundException {

        String resourceName = "config.properties";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();

        try (InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            props.load(resourceStream);
        } catch (Exception e) {
            e.printStackTrace();
        }


        String hostName = props.getProperty("HOST_NAME");
        String dbName = props.getProperty("DB_NAME");
        String user = props.getProperty("USER");
        String password = props.getProperty("PASSWORD");
        String isToEncrypt = props.getProperty("ENCRYPT");

        /*String url = String.format("jdbc:derby://%s:1527;" +
                "database=%s;",// +
                //"user=%s;" +
                //"password=%s;",
                //"encrypt=%s;" +
                //"hostNameInCertificate=*;" +
                //"loginTimeout=30;",
                hostName, dbName, user, password);

        System.out.println(url);*/
        String url = "jdbc:derby://127.0.0.1:1527/testdb;create=true;ssl=peerAuthentication";
        Class.forName("org.apache.derby.jdbc.ClientDriver");

        System.setProperty("javax.net.ssl.keyStore","C:\\Java\\derby\\db-derby-10.13.1.1-bin\\ssl\\clientKeyStore.key");
        System.setProperty("javax.net.ssl.keyStorePassword","qwerty");
        System.setProperty("javax.net.ssl.trustStore","C:\\Java\\derby\\db-derby-10.13.1.1-bin\\ssl\\clientTrustStore.key");
        System.setProperty("javax.net.ssl.trustStorePassword","qwerty");
        //Connection c = DriverManager.getConnection("jdbc:derby://myhost:1527/db;ssl=peerAuthentication");

        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            System.out.println("== connected ==");

            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("FROM *");

            /*while (rs.next()) {
                System.out.println("== result == " + rs.getInt("SUM"));
            }*/
            rs.close();
            stat.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("== disconnected ==");
    }
}
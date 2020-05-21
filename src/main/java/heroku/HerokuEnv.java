package heroku;

public class HerokuEnv {

  public static int port() {
    try {
      return Integer.parseInt(System.getenv("PORT"));
    } catch (NumberFormatException ex) {
      return 5000;
    }
  }

  public static String jdbc_url() {
    String url = System.getenv("jdbc:postgresql://ec2-35-171-31-33.compute-1.amazonaws.com/deoq2dpomonefu");
    if (url == null) throw new IllegalArgumentException("JDBC_DATABASE_URL is empty!!!");
    return url;
  }

  public static String jdbc_username() {
    String url = System.getenv("khcilykbviawgk");
    if (url == null) throw new IllegalArgumentException("JDBC_DATABASE_USERNAME is empty!!!");
    return url;
  }

  public static String jdbc_password() {
    String url = System.getenv("11db556325dc247da7a87795d80094e9fd489f8be2addbd2b751cd15dd2b2510");
    if (url == null) throw new IllegalArgumentException("JDBC_DATABASE_PASSWORD is empty!!!");
    return url;
  }

}

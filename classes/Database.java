import org.json.JSONObject;

public class Database {

  private String reference;
  private String child;
  private String key;
  private static final String extension = ".json";

  public Database(String url) {
    this.reference = url;
    this.child = "";
    this.key = "";
  }

  public Database(String url, String child, String key) {
    this.reference = url;
    this.child = child;
    this.key = key;
  }

  public Database child(String other) {
    String[] keys = other.split("/");
    return new Database(reference, child + "/" + other, keys[keys.length - 1]);
  }

  public Database getMain() {
    return new Database(reference);
  }

  public void setTalker(final DatabaseTalker talker) {
    new Thread(
            new Runnable() {
              public void run() {
                try {
                  String urlString = reference + child + extension;
                  URL url = new URL(urlString);
                  HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                  connection.setRequestMethod("PUT");
                  connection.setConnectTimeout(5000);
                  connection.setReadTimeout(5000);
                  connection.setRequestProperty("Connection", "keep-alive");

                  while (talker.isRunning()) {
                    try {
                      int responseCode = connection.getResponseCode();
                      if (responseCode == HttpURLConnection.HTTP_OK) {
                        AtomicString message = talker.talk();
                        /// Processar resposta do talker: message
                        writeStream(connection.getOutputStream(), message.get());
                      } else {
                        /// Lidar com erro na requisição: responseCode
                        Console.log(readStream(connection.getErrorStream()));
                      }

                    } catch (Exception e) {
                      Console.log(e);
                    }
                  }

                  connection.disconnect();
                } catch (Exception e) {
                  Console.log(e);
                }
              }
            })
        .start();
  }

  public void setListener(final DatabaseListener listener) {
    new Thread(
            new Runnable() {
              public void run() {
                try {
                  String urlString = reference + child + extension;
                  URL url = new URL(urlString);
                  HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                  connection.setRequestMethod("GET");
                  connection.setConnectTimeout(5000);
                  connection.setReadTimeout(5000);
                  connection.setRequestProperty("Connection", "keep-alive");

                  while (listener.isRunning()) {
                    try {
                      int responseCode = connection.getResponseCode();
                      if (responseCode == HttpURLConnection.HTTP_OK) {
                        String response = readStream(connection.getInputStream());
                        final DataSnapshot snapshot = new DataSnapshot(key, new JSONObject(response));
                        Thread.runOnEngine(
                            new Runnable() {
                              public void run() {
                                listener.listen(snapshot);
                              } 
                            });
                      } else {
                        /// Lidar com erro na requisição: responseCode
                        Console.log(readStream(connection.getErrorStream()));
                      }
                    } catch (Exception e) {
                      Console.log(e);
                    }
                  }

                  connection.disconnect();
                } catch (Exception e) {
                  Console.log(e);
                }
              }
            })
        .start();
  }

  public void setTalkerForSingleValueEvent(final DatabaseTalker talker) {
    new Thread(
            new Runnable() {
              public void run() {
                try {
                  String urlString = reference + child + extension;
                  URL url = new URL(urlString);
                  HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                  connection.setRequestMethod("PUT");
                  connection.setConnectTimeout(5000);
                  connection.setReadTimeout(5000);
                  connection.setRequestProperty("Content-Type", "application/json");

                  int responseCode = connection.getResponseCode();
                  if (responseCode == HttpURLConnection.HTTP_OK) {
                    AtomicString message = talker.talk();
                    /// Processar resposta do talker: message
                    writeStream(connection.getOutputStream(), message.get());
                  } else {
                    /// Lidar com erro na requisição: responseCode
                    Console.log(readStream(connection.getErrorStream()));
                  }

                  connection.disconnect();
                } catch (Exception e) {
                  Console.log(e);
                }
              }
            })
        .start();
  }

  public void setListenerForSingleValueEvent(final DatabaseListener listener) {
    new Thread(
            new Runnable() {
              public void run() {
                try {
                  String urlString = reference + child + extension;
                  URL url = new URL(urlString);
                  HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                  connection.setRequestMethod("GET");
                  connection.setConnectTimeout(5000);
                  connection.setReadTimeout(5000);
                  connection.setRequestProperty("Content-Type", "application/json");

                  int responseCode = connection.getResponseCode();
                  if (responseCode == HttpURLConnection.HTTP_OK) {
                    String response = readStream(connection.getInputStream());
                    final DataSnapshot snapshot = new DataSnapshot(key, new JSONObject(response));

                    Thread.runOnEngine(
                        new Runnable() {
                          public void run() {
                            listener.listen(snapshot);
                          }
                        });

                  } else {
                    /// Lidar com erro na requisição: responseCode
                    Console.log(readStream(connection.getErrorStream()));
                  }

                  connection.disconnect();
                } catch (Exception e) {
                  Console.log(e);
                }
              }
            })
        .start();
  }

  private String readStream(InputStream inputStream) throws IOException {
    StringBuilder result = new StringBuilder();

    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    String line;
    while ((line = reader.readLine()) != null) {
      result.append(line).append("\n");
    }
    return result.toString();
  }

  private void writeStream(OutputStream outputStream, String message) throws IOException {
    outputStream.write(message.getBytes());
    outputStream.flush();
  }
}

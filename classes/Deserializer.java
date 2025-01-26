import org.json.JSONObject;

public class Deserializer {

  public static Vector2 toVector2(String json) {
    try {
      JSONObject jsonObject = new JSONObject(json);
      float x = (float) jsonObject.optDouble("x", 0.0);
      float y = (float) jsonObject.optDouble("y", 0.0);
      return new Vector2(x, y);
    } catch (Exception e) {
      Console.log(e);
      return null;
    }
  }

  public static Vector3 toVector3(String json) {
    try {
      JSONObject jsonObject = new JSONObject(json);
      float x = (float) jsonObject.optDouble("x", 0.0);
      float y = (float) jsonObject.optDouble("y", 0.0);
      float z = (float) jsonObject.optDouble("z", 0.0);
      return new Vector3(x, y, z);
    } catch (Exception e) {
      Console.log(e);
      return null;
    }
  }

  public static Vector4 toVector4(String json) {
    try {
      JSONObject jsonObject = new JSONObject(json);
      float x = (float) jsonObject.optDouble("x", 0.0);
      float y = (float) jsonObject.optDouble("y", 0.0);
      float z = (float) jsonObject.optDouble("z", 0.0);
      float w = (float) jsonObject.optDouble("w", 0.0);
      return new Vector4(x, y, z, w);
    } catch (Exception e) {
      Console.log(e);
      return null;
    }
  }
} 

import org.json.JSONObject;

public class Serializer {

  public static String fromVector2(Vector2 vector) {
    try {
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("x", vector.getX());
      jsonObject.put("y", vector.getY());
      return jsonObject.toString();
    } catch (Exception e) {
      Console.log(e);
      return null;
    }
  }

  public static String fromVector3(Vector3 vector) {
    try {
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("x", vector.getX());
      jsonObject.put("y", vector.getY());
      jsonObject.put("z", vector.getZ());
      return jsonObject.toString();
    } catch (Exception e) {
      Console.log(e);
      return null;
    }
  }

  public static String fromVector4(Vector4 vector) {
    try {
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("x", vector.getX());
      jsonObject.put("y", vector.getY());
      jsonObject.put("z", vector.getZ());
      jsonObject.put("w", vector.getW());
      return jsonObject.toString();
    } catch (Exception e) {
      Console.log(e);
      return null;
    }
  }
} 

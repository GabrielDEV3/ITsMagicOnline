///
import org.json.*;

public class DataSnapshot {

  private String key;
  private Object value;

  public DataSnapshot(String key, Object value) {
    this.key = key;
    this.value = value;
  }

  public <T> T getValue(Class<T> clazz) {
    if (value != null) {
      if (value instanceof String && clazz == String.class) {
        String strValue = (String) value;
        if (strValue.startsWith("\"") && strValue.endsWith("\"")) {
          strValue = strValue.substring(1, strValue.length() - 1);
        }
        return clazz.cast(strValue);
      } else if (clazz == Integer.class && value instanceof String) {
        try {
          return clazz.cast(Integer.parseInt((String) value));
        } catch (NumberFormatException e) {
          return null;
        }
      } else if (clazz == Long.class && value instanceof String) {
        try {
          return clazz.cast(Long.parseLong((String) value));
        } catch (NumberFormatException e) {
          return null;
        }
      } else if (clazz == Float.class && value instanceof String) {
        try {
          return clazz.cast(Float.parseFloat((String) value));
        } catch (NumberFormatException e) {
          return null;
        }
      } else if (clazz == Double.class && value instanceof String) {
        try {
          return clazz.cast(Double.parseDouble((String) value));
        } catch (NumberFormatException e) {
          return null;
        }
      } else if (clazz == Boolean.class && value instanceof String) {
        String strValue = ((String) value).toLowerCase();
        if ("true".equals(strValue) || "false".equals(strValue)) {
          return clazz.cast(Boolean.parseBoolean(strValue));
        } else {
          return null;
        }
      } else {
        return clazz.cast(value);
      }
    }
    return null;
  }

  public Object getValue() {
    return value;
  }

  public String getKey() {
    return key;
  }

  public DataSnapshot child(String other) {
    try {
      JSONObject jsonObject = new JSONObject(value.toString());
      if (jsonObject.has(other)) {
        Object childValue = jsonObject.get(other);
        String childValueString = (childValue != null) ? childValue.toString() : null;
        return new DataSnapshot(other, childValueString);
      }
    } catch (Exception e) {
      Console.log(e);
    }
    return null;
  }

  public boolean hasChild(String key) {
    try {
      JSONObject jsonObject = new JSONObject(value.toString());
      Object childValue = jsonObject.opt(key);
      return (childValue != null);
    } catch (Exception e) {
      Console.log(e);
    }
    return false;
  }

  public boolean hasChildren() {
    try {
      JSONObject jsonObject = new JSONObject(value.toString());
      return jsonObject.length() > 0;
    } catch (Exception e) {
      Console.log(e);
    }
    return false;
  }

  public List<DataSnapshot> getChildren() {
    try {
      JSONObject jsonObject = new JSONObject(value.toString());
      JSONArray keys = jsonObject.names();
      if (keys != null) {
        List<DataSnapshot> snaps = new ArrayList<DataSnapshot>();
        for (int i = 0; i < keys.length(); i++) {
          String childKey = keys.getString(i);
          Object childValue = jsonObject.get(childKey);
          String childValueString = (childValue != null) ? childValue.toString() : null;
          snaps.add(new DataSnapshot(childKey, childValueString));
        }
        return snaps;
      }
    } catch (Exception e) {
      Console.log(e);
    }
    return null;
  }

  public Iterator<DataSnapshot> iterate() {
    return getChildren().iterator();
  } 

  public long getChildrenCount() {
    return (long) getChildren().size();
  }

  public boolean exists() {
    if (value != null) {
      return true;
    }
    return false;
  }

  public String toString() {
    return value.toString();
  }
}

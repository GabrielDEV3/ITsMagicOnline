import java.util.concurrent.atomic.AtomicReference;

public class AtomicString {

  private final AtomicReference<String> atomicString;

  public AtomicString(String initialValue) {
    atomicString = new AtomicReference<String>(initialValue);
  }

  public String get() {
    return atomicString.get();
  }

  public void set(String newValue) {
    atomicString.set(newValue);
  }

  public void append(String additionalValue) {
    String oldValue;
    do {
      oldValue = atomicString.get();
    } while (!atomicString.compareAndSet(oldValue, oldValue + additionalValue));
  }

  public boolean compareAndSet(String expectedValue, String newValue) {
    return atomicString.compareAndSet(expectedValue, newValue);
  } 
}

public interface DatabaseListener {
  public boolean isRunning();
  public void listen(DataSnapshot value);
} 

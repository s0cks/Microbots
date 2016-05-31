package microbots.api;

public interface IRobitPartManager{
  public void registerPart(IRobitPart part);
  public IRobitPart getPart(String id);
}
package allliveyoung.wms.constant;

public enum StoreTemperature {

  ROOM_TEMPERATURE("상온") , REFRIGERATED("냉장") , FROZEN("냉동");
  private final String description;

  StoreTemperature(String description){
    this.description=description;
  }

  public String getDescription(){
    return description;
  }
}

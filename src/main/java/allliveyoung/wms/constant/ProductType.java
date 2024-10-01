package allliveyoung.wms.constant;

public enum ProductType {
  DRUG("마약") , EXPLOSIVE("폭발물") , BIOLOGICAL("생물학적제제") , NORMAL("일반");
  private final String description;

  ProductType(String description){
    this.description=description;
  }

  public String getDescription(){
    return description;
  }


}

package allliveyoung.wms.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * multipart 파일을 저장하는 역할을 함
 */
@Component
@Slf4j
public class FileStore {

  @Value("${file.dir}")
  private String fileDir; //이 경로에 사용자가 첨부한 파일들 저장


  public String getFullPath(String filename){
    return fileDir + filename+".png"; //파일 경로 + 파일명
  }

}

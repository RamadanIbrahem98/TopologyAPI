package io.RamadanIbrahem98.TopologyAPI.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TopologyNotFoundException extends RuntimeException {

  public TopologyNotFoundException(String msg) {
    super(msg);
  }
}
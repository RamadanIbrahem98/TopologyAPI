package io.RamadanIbrahem98.TopologyAPI.Exception;

import java.util.Date;

public class ExceptionResponse {
  private final Date timestamp;
  private final String message;
  private final String path;
  private final String httpCodeMessage;

  public ExceptionResponse(Date timestamp, String message, String path, String httpCodeMessage) {
    this.timestamp = timestamp;
    this.message = message;
    this.path = path;
    this.httpCodeMessage = httpCodeMessage;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public String getMessage() {
    return message;
  }

  public String getDetails() {
    return path;
  }

  public String getHttpCodeMessage() {
    return httpCodeMessage;
  }
}

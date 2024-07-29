package com.sonic.studentappsonic.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ApiResponse<T> {
  private final Status status;

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private List<T> results;

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private Metadata metadata;

  //error를 담아야함
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private Object data;

  //생성자 - 정상응답
  public ApiResponse(List<T> results) {
    this.status = new Status(2000, "OK");
    this.results = results;
    this.metadata = new Metadata(results.size());
  }


  //생성자 - 에러응답
  public ApiResponse(int code, String message, Object data) {
    this.status = new Status(code, message);
    this.data = data;
  }

  @Getter
  @AllArgsConstructor
  private static class Status {
    private int code;
    private String message;
  }

  @Getter
  @AllArgsConstructor
  private static class Metadata {
    private int resultCount = 0;
  }

}

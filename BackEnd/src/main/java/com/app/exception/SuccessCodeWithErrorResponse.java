package com.app.exception;

import lombok.Getter;


public class SuccessCodeWithErrorResponse extends RuntimeException {

  @Getter
  private ErrorResponse errorResponse;

  @Getter
  private long id;

  public SuccessCodeWithErrorResponse(Long id, ErrorResponse errorResponse) {
    this.id = id;
    this.errorResponse = errorResponse;
  }

  public SuccessCodeWithErrorResponse(ErrorResponse errorResponse) {
    this.errorResponse = errorResponse;
  }

}

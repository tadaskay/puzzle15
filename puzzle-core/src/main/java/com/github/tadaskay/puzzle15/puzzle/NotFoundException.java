package com.github.tadaskay.puzzle15.puzzle;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException extends RuntimeException {
}

package com.xsis.bc345.be.util.error;

import java.time.LocalDateTime;

public record ErrorMessage(int statusCode, String message, String cause, LocalDateTime time) {
}

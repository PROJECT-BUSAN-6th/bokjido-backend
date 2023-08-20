package com.projectbusan.bokjido.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode{
    AUTHENTICATION_FAILED(401, "AUTHENTICATION_FAILED", "AUTHENTICATION FAILED ERROR"),
    USER_NOT_FOUND_ERROR(400, "USER-NOT_FOUND", "USER NOT FOUND ERROR"),
    BENEFITS_NOT_FOUND_ERROR(400, "BENEFITS-NOT-FOUND", "BENEFITS NOT FOUND ERROR"),
    BENEFIT_REVIEW_NOT_FOUND_ERROR(400, "BENEFIT-REVIEW-NOT-FOUND", "BENEFIT REVIEWS NOT FOUND ERROR"),
    FACILITY_NOT_DELETABLE(400, "FACILITY_NOT_DELETABLE", "FACILITY NOT DELETABLE"),
    INVALID_ENUM_VALUE(400, "INVALID_ENUM_VALUE", "ENUM VALUE IS INVALID"),


    METHOD_ARGUMENT_TYPE_MISMATCHED(400, "METHOD_ARGUMENT_TYPE_MISMATCHED", "METHOD ARGUMENT TYPE MISMATCHED"),
    PARAMETER_BINDING_ERROR(400, "PARAMETER_BINDING_ERROR", "PARAMETER BINDING ERROR"),
    METHOD_NOT_ALLOWED(405, "METHOD_NOT_ALLOWED", "METHOD NOT ALLOWED"),
    INTERNAL_SERVER_ERROR(500, "INTERNAL-SERVER-ERROR", "INTERNAL SERVER ERROR");

    private final int status;
    private final String errorType;
    private final String message;
}
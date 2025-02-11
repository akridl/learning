package com.example;

import lombok.Getter;

@Getter
public enum AlignmentSubOperation {

    SCM_CLONE("scm-clone"),
    ALIGN("align"),
    PUSH_RESULT("push-result");

    private final String operation;

    AlignmentSubOperation(String operation) {
        this.operation = operation;
    }
}

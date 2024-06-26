package com.example.validation;

import jakarta.validation.groups.Default;

public interface ValidationGroup {

    interface WhenCreating extends Default {}

    interface WhenModifying extends Default {}
}

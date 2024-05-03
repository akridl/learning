# Validation demo

- using [Jakarta Validation Specification](https://jakarta.ee/specifications/bean-validation/)
- basically 3 steps are enough:
    1) Define validation annotations (either existing or own) at DTO
        - see `PersonEditDto`
    2) Force validation of the DTO when taking it as argument in the controller method
        - see `PersonController`
    3) Potentially define additional checks in the model
        - these validations cannot be done at DTO level, e.g. uniqueness of email
        - see `Person`
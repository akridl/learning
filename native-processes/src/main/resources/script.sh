#!/bin/bash

echo "Running the script $0";
echo "The value of env variable FOO is: $FOO";

# Print to stderr
>&2 echo "Am I rlly printed to stderr? WTF -_-"

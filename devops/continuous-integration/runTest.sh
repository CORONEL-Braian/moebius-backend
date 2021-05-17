#!/bin/bash

# shellcheck disable=SC2016
: '
Run tests for all modules

PRECONDITION: The modules have `tasks.test { useJUnitPlatform() }` in gradle. Because when the tests modules are builded
with the command, the result is always "BUILD SUCCESSFUL" although some test module fail.
'

# TODO: Refactor change project root
cd ../..
pwd

#./gradlew :feature_sign_up:test
./gradlew :backend:test

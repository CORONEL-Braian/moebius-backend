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

./gradlew :api-core:test
./gradlew :backend:test
./gradlew :common-feature:test
./gradlew :common-feature-test-utils:test
./gradlew :data:test
./gradlew :data-core:test
./gradlew :deceased-core:test
./gradlew :domain:test
./gradlew :feature_credential_managment:test
./gradlew :feature_legal:test
./gradlew :feature_login:test
./gradlew :feature_sign_up:test
./gradlew :feature_third_profile:test
./gradlew :infrastructure-core:test
./gradlew :json-api:test
./gradlew :library_base:test
./gradlew :library_test_utils:test
./gradlew :secret_manager:test
./gradlew :security-core:test
./gradlew :service:test
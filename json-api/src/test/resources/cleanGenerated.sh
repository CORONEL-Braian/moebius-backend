#!/bin/bash

: '
Recursively clean all json files of generated directory

https://askubuntu.com/a/377442/676466
'

# shellcheck disable=SC2154
DIR="$(pwd)/generated"
if [ -d "$DIR" ]; then
  cd generated || exit
  echo "The following files (not directories) will be deleted of ${DIR}"

#  Show files to delete
  find . -name "*.json" -type f

#  Delete files
  find . -name "*.json" -type f -delete
fi
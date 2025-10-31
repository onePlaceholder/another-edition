#/usr/bin/env bash

mods_folder=$(pwd)/mods
coremod_folder=$(pwd)/coremod

version=$(grep -E '^[[:space:]]*mod_version[[:space:]]*=' "${coremod_folder}/gradle.properties" \
  | grep -v '^[[:space:]]*#' \
  | sed -E 's/^[[:space:]]*mod_version[[:space:]]*=[[:space:]]*//' \
  | sed 's/[[:space:]]*$//')

touch ${mods_folder}/another_edition_core-.jar
rm ${mods_folder}/another_edition_core-*.jar

cd $coremod_folder
${coremod_folder}/gradlew build


mv ${coremod_folder}/build/libs/another_edition-${version}.jar ${mods_folder}/another_edition_core-${version}.jar
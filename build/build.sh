#/usr/bin/env bash

pack_file=$(pwd)/../pack.toml
commit_hash=$(git rev-parse --short=6 HEAD)
version=$(grep -E '^version\s*=' $pack_file | sed -E 's/version\s*=\s*"([^"]+)"/\1/')

output_name=another_edition-${version}-${commit_hash}.mrpack

packwiz mr export --pack-file $pack_file --output $output_name
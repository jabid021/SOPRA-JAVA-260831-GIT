#! /usr/bin/bash

cd "$(dirname "$0")/.." || exit 1


for file in assets/images/hedgehogs_gallery_gifs/*.gif; do
    basename=$(basename "$file")
    new_basename=$(printf '%s' "$basename" | sed 's/ //g')
    new_path="$(dirname "$file")/$new_basename"
    
    if [[ "$file" != "$new_path" ]]; then
        mv -n "$file" "$new_path"
        echo "$basename renommé en $new_basename"
    fi
done
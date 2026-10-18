#! /usr/bin/bash

# Se placer à la racine du projet
cd "$(dirname "$0")/.." || exit 1

mkdir -p assets/data

if [[ -f assets/data/gallery.json ]]; then
    rm assets/data/gallery.json
fi

# Générer la liste des images
find assets/images/hedgehogs_gallery_gifs -type f -iname "*.gif" \
    -print0 |
    sort -z |
    jq -Rs 'split("\u0000") | map(select(length > 0))' \
    > assets/data/gallery.json

echo "Galerie JSON générée avec succès."
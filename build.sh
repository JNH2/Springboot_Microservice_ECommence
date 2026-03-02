#!/usr/bin/env bash
# simple build helper for this project

set -euo pipefail          # fail on error / undefined var
cd "$(dirname "$0")/productservice" || exit 1

echo "⏳ building productservice…"
./mvnw clean install -DskipTests

echo "✅ build succeeded"
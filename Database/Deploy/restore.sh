#!/bin/bash
set -e

# ENV einlesen
source "$(dirname "$0")/../.env"

SCRIPTS_DIR="$(dirname "$0")/../DeltaScripts"

echo "🧨 Dropping & Recreating '$DB_NAME'..."
mysql -u"$DB_USER" -p"$DB_PASS" -h"$DB_HOST" -P"$DB_PORT" -e "DROP DATABASE IF EXISTS \`$DB_NAME\`;"
mysql -u"$DB_USER" -p"$DB_PASS" -h"$DB_HOST" -P"$DB_PORT" -e "CREATE DATABASE \`$DB_NAME\`;"

echo "📦 Erstelle schema_version Tabelle..."
mysql -u"$DB_USER" -p"$DB_PASS" -h"$DB_HOST" -P"$DB_PORT" "$DB_NAME" <<SQL
CREATE TABLE IF NOT EXISTS schema_version (
    id INT AUTO_INCREMENT PRIMARY KEY,
    script_name VARCHAR(255) NOT NULL,
    executed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
SQL

echo "🚀 Führe alle DeltaScripts aus..."
for script_path in $(ls "$SCRIPTS_DIR"/*.sql | sort); do
  script_name=$(basename "$script_path")
  echo "👉 Führe $script_name aus..."
  mysql -u"$DB_USER" -p"$DB_PASS" -h"$DB_HOST" -P"$DB_PORT" "$DB_NAME" <<SQL
START TRANSACTION;
SOURCE $script_path;
INSERT INTO schema_version (script_name) VALUES ('$script_name');
COMMIT;
SQL
  echo "✅ $script_name erfolgreich."
done

echo "🎉 Restore abgeschlossen!"
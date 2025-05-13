#!/bin/bash
set -e

source "$(dirname "$0")/../.env"

SCRIPTS_DIR="$(dirname "$0")/../DeltaScripts"

echo "📦 Check if schema_version exists ..."
mysql -u"$DB_USER" -p"$DB_PASS" -h"$DB_HOST" -P"$DB_PORT" "$DB_NAME" <<SQL
CREATE TABLE IF NOT EXISTS schema_version (
    id INT AUTO_INCREMENT PRIMARY KEY,
    script_name VARCHAR(255) NOT NULL,
    executed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
SQL

echo "🔍 Get executed Scripts..."
executed_scripts=$(mysql -N -u"$DB_USER" -p"$DB_PASS" -h"$DB_HOST" -P"$DB_PORT" -D"$DB_NAME" \
  -e "SELECT script_name FROM schema_version;" | sort)

for script_path in $(ls "$SCRIPTS_DIR"/*.sql | sort); do
  script_name=$(basename "$script_path")
  if echo "$executed_scripts" | grep -qx "$script_name"; then
    echo "⏭️  $script_name skipping."
  else
    echo "🚀 Executing $script_name..."
    mysql -u"$DB_USER" -p"$DB_PASS" -h"$DB_HOST" -P"$DB_PORT" "$DB_NAME" <<SQL
START TRANSACTION;
SOURCE $script_path;
INSERT INTO schema_version (script_name) VALUES ('$script_name');
COMMIT;
SQL
    echo "✅ $script_name applied successfully."
  fi
done

echo "🎉 All updates completed."
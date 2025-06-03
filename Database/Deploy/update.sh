#!/bin/bash
# -----------------------------------------------------------------------------
# update.sh - Applies new SQL scripts from DeltaScripts to the PrimeDrive DB.
#
# Checks for already executed scripts via schema_version table and only applies new ones.
# Ensures each script is wrapped in a transaction for consistency.
#
# Author: Fatlum Epiroti
# Version: 1.0.0
# Date: 2025-06-03
# -----------------------------------------------------------------------------

# Exit script immediately on error
set -e

# Load environment variables from .env file
source "$(dirname "$0")/../.env"

# Define directory containing delta scripts
SCRIPTS_DIR="$(dirname "$0")/../DeltaScripts"

# Ensure the schema_version table exists to track script execution
echo "📦 Check if schema_version exists ..."
mysql -u"$DB_USER" -p"$DB_PASS" -h"$DB_HOST" -P"$DB_PORT" "$DB_NAME" <<SQL
CREATE TABLE IF NOT EXISTS schema_version (
    id INT AUTO_INCREMENT PRIMARY KEY,
    script_name VARCHAR(255) NOT NULL,
    executed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
SQL

# Fetch names of scripts that have already been executed
echo "🔍 Get executed Scripts..."
executed_scripts=$(mysql -N -u"$DB_USER" -p"$DB_PASS" -h"$DB_HOST" -P"$DB_PORT" -D"$DB_NAME" \
  -e "SELECT script_name FROM schema_version;" | sort)

# Loop through all SQL scripts and execute only the new ones
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

# Final output
echo "🎉 All updates completed."
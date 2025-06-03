#!/bin/bash
# -----------------------------------------------------------------------------
# restore.sh - Reinitializes the MySQL database for PrimeDrive from DeltaScripts.
#
# Drops and recreates the database, creates a schema versioning table,
# and sequentially executes all SQL scripts located in DeltaScripts/.
#
# Author: Fatlum Epiroti
# Version: 1.0.0
# Date: 2025-06-03
# -----------------------------------------------------------------------------
set -e

# Load environment variables from .env
source "$(dirname "$0")/../.env"

# Set path to SQL script directory
SCRIPTS_DIR="$(dirname "$0")/../DeltaScripts"

# Drop and recreate the database
echo "🧨 Dropping & Recreating '$DB_NAME'..."
mysql -u"$DB_USER" -p"$DB_PASS" -h"$DB_HOST" -P"$DB_PORT" -e "DROP DATABASE IF EXISTS \`$DB_NAME\`;"
mysql -u"$DB_USER" -p"$DB_PASS" -h"$DB_HOST" -P"$DB_PORT" -e "CREATE DATABASE \`$DB_NAME\`;"

# Create version tracking table if it doesn't exist
echo "📦 Creating schema_version..."
mysql -u"$DB_USER" -p"$DB_PASS" -h"$DB_HOST" -P"$DB_PORT" "$DB_NAME" <<SQL
CREATE TABLE IF NOT EXISTS schema_version (
    id INT AUTO_INCREMENT PRIMARY KEY,
    script_name VARCHAR(255) NOT NULL,
    executed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
SQL

# Execute each .sql script in sorted order and track in schema_version
echo "🚀 Executing DeltaScripts..."
for script_path in $(ls "$SCRIPTS_DIR"/*.sql | sort); do
  script_name=$(basename "$script_path")
  echo "👉 Execute $script_name..."
  mysql -u"$DB_USER" -p"$DB_PASS" -h"$DB_HOST" -P"$DB_PORT" "$DB_NAME" <<SQL
START TRANSACTION;
SOURCE $script_path;
INSERT INTO schema_version (script_name) VALUES ('$script_name');
COMMIT;
SQL
  echo "✅ $script_name successfully."
done

# Final output
echo "🎉 Restore end!"
#!/bin/bash
#
# -----------------------------------------------------------------------------
# Script: generate-ssl.sh
# Purpose: Generates a self-signed SSL certificate for local Spring Boot HTTPS setup.
# Usage: ./generate-ssl.sh
# Author: Fatlum Epiroti
# Version: 1.0
# Date: 2025-06-03
# -----------------------------------------------------------------------------

# === CONFIGURATION ===
KEYSTORE_NAME="keystore.p12"
KEYSTORE_PASSWORD="password"
ALIAS="springboot-local"
VALIDITY_DAYS=3650
DISTINGUISHED_NAME="CN=localhost, OU=LocalDev, O=PrimeDrive, L=Zürich, ST=ZH, C=CH"
KEYALG="RSA"
KEYSIZE=2048
STORETYPE="PKCS12"
TARGET_PATH="src/main/resources"

# === EXECUTION ===
# Generate a self-signed SSL certificate using keytool
echo "🔐 Generating self-signed certificate for Spring Boot HTTPS..."

keytool -genkeypair \
  -alias "$ALIAS" \
  -keyalg "$KEYALG" \
  -keysize "$KEYSIZE" \
  -storetype "$STORETYPE" \
  -keystore "$KEYSTORE_NAME" \
  -validity "$VALIDITY_DAYS" \
  -storepass "$KEYSTORE_PASSWORD" \
  -dname "$DISTINGUISHED_NAME"

# Move the generated keystore to the Spring Boot resources directory
echo "📦 Moving keystore to $TARGET_PATH..."
mkdir -p "$TARGET_PATH"
mv "$KEYSTORE_NAME" "$TARGET_PATH/"

# Output the location and password of the generated keystore
echo "✅ Certificate ready at: $TARGET_PATH/$KEYSTORE_NAME"
echo "📌 Store password: $KEYSTORE_PASSWORD"
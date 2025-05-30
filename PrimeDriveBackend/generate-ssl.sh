#!/bin/bash

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

echo "📦 Moving keystore to $TARGET_PATH..."
mkdir -p "$TARGET_PATH"
mv "$KEYSTORE_NAME" "$TARGET_PATH/"

echo "✅ Certificate ready at: $TARGET_PATH/$KEYSTORE_NAME"
echo "📌 Store password: $KEYSTORE_PASSWORD"
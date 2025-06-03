#!/bin/bash
#
# -----------------------------------------------------------------------------
# Script: zap-api-scan.sh
# Purpose: Executes an automated OWASP ZAP API scan using a bearer token for authentication.
# Usage: ./zap-api-scan.sh
# Author: Fatlum Epiroti
# Version: 1.0
# Date: 2025-06-03
# -----------------------------------------------------------------------------

read -s -p "Enter Bearer Token: " token
echo
set -x

TIMESTAMP=$(date +"%Y%m%d-%H%M%S")

# Create script file for header injection
echo "
function sendingRequest(msg, initiator, helper) {
    msg.getRequestHeader().setHeader('Authorization', 'Bearer $token');
}
function responseReceived(msg, initiator, helper) {}
" > ./auth-header.js

docker network create zap-net || true

docker run -u zap \
  --network zap-net \
  --name zapscanner \
  -v $(pwd)/zap-reports:/zap/wrk/:rw \
  -v $(pwd)/auth-header.js:/zap/scripts/auth-header.js \
  -d \
  ghcr.io/zaproxy/zaproxy:stable zap.sh -daemon -port 8090 -config api.disablekey=true

# Wait for ZAP to be ready
sleep 15

# Load and enable the script
docker exec zapscanner curl "http://localhost:8090/JSON/script/action/load/?scriptName=auth-header&scriptType=httpsender&scriptEngine=ECMAScript&fileName=auth-header.js&scriptDescription=AddAuthHeader"
docker exec zapscanner curl "http://localhost:8090/JSON/script/action/enable/?scriptName=auth-header"

# Run the API scan
docker exec zapscanner zap-api-scan.py \
  -t http://host.docker.internal:8080/v3/api-docs \
  -f openapi \
  -r zap-api-report-${TIMESTAMP}.html \
  -I
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

# Start ZAP daemon
docker run -u zap \
  -v $(pwd)/zap-reports:/zap/wrk/:rw \
  -v $(pwd)/auth-header.js:/zap/auth-header.js \
  -d -p 8090:8090 \
  ghcr.io/zaproxy/zaproxy:stable zap.sh -daemon -port 8090 -config api.disablekey=true

# Wait for ZAP to be ready
sleep 15

# Load and enable the script
curl "http://localhost:8090/JSON/script/action/load/?scriptName=auth-header&scriptType=httpsender&scriptEngine=ECMAScript&fileName=/zap/auth-header.js"
curl "http://localhost:8090/JSON/script/action/enable/?scriptName=auth-header"

# Run the API scan
docker exec $(docker ps -qf ancestor=ghcr.io/zaproxy/zaproxy:stable) zap-api-scan.py \
  -t http://host.docker.internal:8080/v3/api-docs \
  -f openapi \
  -r zap-full-scan-report-${TIMESTAMP}.html \
  -I
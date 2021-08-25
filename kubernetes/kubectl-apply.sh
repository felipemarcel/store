#!/bin/bash

usage(){
 cat << EOF

 Usage: $0 -f
 Description: To apply k8s manifests using the default \`kubectl apply -f\` command

EOF
exit 0
}

logSummary() {
    echo ""
}

default() {
    suffix=k8s
    kubectl apply -f namespace.yaml
    kubectl apply -f store-${suffix}/
}

[[ "$@" =~ ^-[fks]{1}$ ]]  || usage;

while getopts ":fks" opt; do
    case ${opt} in
    f ) echo "Applying default \`kubectl apply -f\`"; default ;;
    \? | * ) usage ;;
    esac
done

logSummary

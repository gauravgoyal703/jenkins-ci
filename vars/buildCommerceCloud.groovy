def call(branch, buildName) {
    echo "##### Initiate Build to SAP Commerce Cloud Environment #####"
    //deploy tag 
    script{
        withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'commerceCloudCredentials', usernameVariable: 'subscriptionId', passwordVariable: 'token']]) {
            portalURL = "https://portalrotapi.hana.ondemand.com/v2/subscriptions/${subscriptionId}/builds";
            authorizationValue = "Bearer ${token}"
            build = sh (script: "curl --location --request POST ${portalURL} --header 'Content-Type: application/json' --header 'Authorization: ${authorizationValue}' --header 'Content-Type: text/plain' --data-raw '{\"branch\": \"${branch}\",\"name\": \"${buildName}\"}'",returnStdout:true)
            echo "$build"
            build_result = readJSON text: "$build"
            code_number = build_result["code"]
            return code_number
        }
    }
}  

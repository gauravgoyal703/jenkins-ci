def call(branch, buildName) {
    echo "##### Initiate Build to SAP Commerce Cloud Environment #####"
    //deploy tag 
    script{
        withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'commerceCloudCredentials', usernameVariable: 'SUBSCRIPTIONID', passwordVariable: 'TOKEN']]) {
            portalURL = 'https://portalrotapi.hana.ondemand.com/v2/subscriptions/$SUBSCRIPTIONID/builds';
            authorizationValue = 'Bearer $TOKEN'
            echo "$portalURL"
            echo "$authorizationValue"
            build = sh (script: "curl --location --request POST ${portalURL} --header 'Content-Type: application/json' --header 'Authorization: ${authorizationValue}' --header 'Content-Type: text/plain' --data-raw '{\"branch\": \"${branch}\",\"name\": \"${buildName}\"}'",returnStdout:true)
            echo "$build"
            build_result = readJSON text: "$build"
            code_number = build_result["code"]
            return code_number
        }
    }
}  

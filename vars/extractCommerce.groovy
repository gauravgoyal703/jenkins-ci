def call(commerceDir) {
    echo "##### Extract commerce platform ##### -- ${commerceDir}"
    sh "unzip -o ../CXCOMM2011*.ZIP -d ${commerceDir}/core-customize"
    /** Uncomment if you will be using the Integration Extension Pack
    echo "##### Extract commerce integration pack #####"
    sh "unzip -o ../CXCOMINTPK2011*.ZIP -d ${commerceDir}/core-customize"
    **/
}  

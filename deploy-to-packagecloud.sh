rm -rf target
rm dependency-reduced-pom.xml
ls

echo Fetching any potential remote changes...
git fetch --all
git pull --all

if ! [[ -f "./mvnw" ]]; then
  echo "The file, ``./mvnw``, does not exist."
  echo "Running ``mvn -N wrapper:wrapper`` to generate ``./mvnw``..."
  mvn -N wrapper:wrapper
fi

echo Fetching project metadata...
project_version=`./mvnw help:evaluate -Dexpression=project.version -q -DforceStdout`
project_name=`./mvnw help:evaluate -Dexpression=project.name -q -DforceStdout`
project_groupId=`./mvnw help:evaluate -Dexpression=project.groupId -q -DforceStdout`
project_artifactId=`./mvnw help:evaluate -Dexpression=project.artifactId -q -DforceStdout`
project_version=`./mvnw help:evaluate -Dexpression=project.version -q -DforceStdout`
project_build_directory=`./mvnw help:evaluate -Dexpression=project.build.directory`
package_cloud_username=""
package_cloud_packagename=""
read -p "Enter PackageCloud username: " package_cloud_username
read -p "Enter PackageCloud package name: " package_cloud_packagename

./mvnw package -Dmaven.test.skip=true
package_cloud push $package_cloud_username/$package_cloud_packagename ./target/$project_artifactId-$project_version.jar --coordinates=$project_groupId:$project_artifactId:$project_version
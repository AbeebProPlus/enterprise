build your imagr with the following command
./mvnw spring-boot:build-image "-Dspring-boot.build-image.imageName=mac8ver1/registry"

kubectl apply -f first-pod.yaml --- to create a pod, it will update if it already exists
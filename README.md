# tui
Steps :

git clone {gitURL}
mvn clean install
Select project and Run as Java Application
Use the below curl in Postman
1. Success scenario :
curl --location --request GET 'localhost:8080/v1/repository/owner/maheshbobby/' \
   --header 'x-JourneyID: 0becf108-c46d-4c41-9622-82a8beae4b87' \
   --header 'x-SourceSystemID: 123' \
   --header 'x-CreateDateTime: 2022-05-13T14:13:08Z' \
   --header 'Accept: application/json' \
   --header 'Content-Type: application/json'
2. Curl for Missing Accept Header Scenario:
curl --location --request GET 'localhost:8080/v1/repository/owner/maheshbobby/' \
   --header 'x-JourneyID: 0becf108-c46d-4c41-9622-82a8beae4b87' \
   --header 'x-SourceSystemID: 123' \
   --header 'x-CreateDateTime: 2022-05-13T14:13:08Z' \
   --header 'Content-Type: application/json'

3. Response for Missing Accept Header Scenario:   
     {
         "status": "406 NOT_ACCEPTABLE",
         "message": "Media Type Not Acceptable"
      }
4. Curl for Invalid GIT User
   curl --location --request GET 'localhost:8080/v1/repository/owner/dummy/' \
   --header 'x-JourneyID: 0becf108-c46d-4c41-9622-82a8beae4b87' \
   --header 'x-SourceSystemID: 123' \
   --header 'x-CreateDateTime: 2022-05-13T14:13:08Z' \
   --header 'Accept: application/json' \
   --header 'Content-Type: application/json'


4. Response for Invalid GIT User
  {
   "code": "404 NOT_FOUND",
   "message": "Given User doesn't Exist"
   }
5. Swagger absolute path src/main/resources/api-clients-swagger/RepoDetails.yaml
      It will generate model in the models in target folder during compilation 

#### 0. Specification
- Spring Boot with Kotlin
- Dependencies
    - Hibernate
    - H2Database
    - Jackson Module
    - Kotlinx Serialization
#### 1. Root Directory
-  `src\main\kotlin\com\holme\be_app`
    - `BeAppApplication.kt`: Spring  Boot Application entrypoint.
- Sub directories
    - `\utils`: Contains utility classes and functions.
    - `\repository`: Contains service database repository used universally in backend service.
    - `\entity`: Contains database entity definitions
    - `\dto`: Contains dto class for database entity used universally in backend service.
    - `\config`: Contains configuration settings for backend service.
    - `\api`: Contains API definition of backend service.

### 2. `\api` Directory
- `\entity`: Contains entities used for api service. (e.g. Response, Request, etc)
    - `\instance\Instances.kt`: Entity Definition for Instance (Used for MATTER communication with GoLang IOT Instances.)
    - `\request`: Entities used for request of each endpoints
        - `ServiceRequest.kt`: Interface for service request.
        - `SingleRequest.kt`: Class used for single service request.
        - `MultipleRequests.kt`: Class used for multiple service request.
    - `\response`: Entities used for response of each endpoints
        - `ServiceResponse.kt`: Interface for service response.
        - `SingleResponse.kt`, `SingleResponseService.kt`: Class and Service used for single service response.
        - `MultipleResponses.kt`, `MultipleResonseService.kt`: Class and Service used for multiple service response.
- `\ping`: Ping service directory; contains service to check whether the IOT instance exists in targeting place.
    - `\controller\PingController.kt`:  Controller for ping service.
        - Endpoint: `/api/v1/ping/`
    - `\entity\PingRequest.kt`, `\entity\PingResonse.kt`: Request/Response class definition for Ping Service
    - `\manager\InstaneMapManager.kt`: Manager class for ping response. It handles the IOT instance doesn't exists.
    - `\service\PingService.kt`: Ping service class. Contains business logic for ping service.
- `\report`: Report service directory; contains service for report regarding setting substitution and upgrades
    - `\controller\ReportController.kt`:  Controller for report service.
        - Endpoint for report generation: `/api/v1/report/generate`
        - Endpoint for fetching all reports: `/api/v1/report/fetchAll`
        - Endpoint for fetch reports based on types: `/api/v1/report/withType`
        - Endpoint check reports: `/api/v1/report/check`
    - `\entity\ReportRequest.kt`, `\entity\ReportResonse.kt`: Request/Response class definition for Report Service
    - `\service\ReportService.kt`: Report service class. Contains business logic for report service.
- `\setting`: Setting service directory; it will be divided into uploading service and downloading service.
    - `\upload`: Setting upload service directory; contains service to upload IOT instance setting to database.
        - `\controller\UploadController.kt`:  Controller for upload service.
            - Endpoint: `/api/v1/setting/upload/`
        - `\entity\UploadRequest.kt`, `\entity\UploadResonse.kt`: Request/Response class definition for Setting Upload Service
        - `\service\UploadService.kt`: Setting upload service class. Contains business logic for setting upload service.
        - `\test\_REST_TEST_DATA.json`: Contains test data for testing setting uploads.
    - `\download`: Setting download service directory; contains service to download IOT instance setting to database.
        - `\controller\DownloadController.kt`:  Controller for download service.
            - Endpoint: `/api/v1/setting/download/`
        - `\entity\DownloadRequest.kt`, `\entity\DownloadResonse.kt`: Request/Response class definition for Setting download service
        - `\service\DownloadService.kt`: Setting download service class. Contains business logic for setting download service.
- `\signin`: Sign in service directory; contains service for signing in.
    - `\controller\SignInController.kt`:  Controller for sing in service.
        - Endpoint: `/api/v1/signin/`
    - `\entity\SignInRequest.kt`, `\entity\SignInResonse.kt`: Request/Response class definition for Sign in service
    - `\service\SignInService.kt`: Sign in service class. Contains business logic for sign in service.
- `\signup`: Sign up service directory; contains service for signing in.
    - `\controller\SignUpController.kt`:  Controller for sing up service.
        - Endpoint: `/api/v1/signup/`
    - `\entity\SignUpRequest.kt`, `\entity\SignUpResonse.kt`: Request/Response class definition for Sign up service
    - `\service\SignUpService.kt`: Sign up service class. Contains business logic for sign up service.
- `\sync`: Setting synchronization service directory; contains service to synchronize and apply IOT instance settings to targeting place.
    - `\controller\SyncController.kt`:  Controller for synchronization service.
        - Endpoint: `/api/v1/sync/request`
    - `\entity\SyncRequest.kt`, `\entity\SyncResonse.kt`: Request/Response class definition for sync Service
    - `\factory\SyncInstanceTypeFactory.kt`: Return a class for instance. Used a factory pattern to do such task.
    - `\manager\SubroutineManager.kt`: Manager class for sub-routines; apply subroutines based on the ping result.
    - `\service\SyncRequestService.kt`: Synchronization service class. Contains business logic for synchronization service.
    - `\test\sync_test_data.json`: Contains test data for testing setting synchronization.

#### 3. `\utils` Directory
- `HashFunction.kt`: Apply SHA Hash for secret value. Used for universal encryption in backend service.

#### 4. `\repository` Directory
- `ServiceUserRepository.kt`: Database repository for `ServiceUser` table.
- `SettingReposiory.kt`:  Database repository for `InstanceSetting` table.
- `ReportRepository.kt`: Database repository for `Report` table.

### 5. `\entity` Directory
- `ServiceUser.kt`: Database entity for `ServiceUser` table.
- `InstanceSetting.kt`:  Database entity for `InstanceSetting` table.
- `Report.kt`: Database entity for `Report` table.

### 6. `\dto` Directory
- `ServiceUserDto.kt`: Dto class for `ServiceUser` entity.
- `SafeServiceUserDto.kt`: Safe dto class for `ServiceUser` entity. (Dto class that doesn't contains password and user secret information.)
- `InstanceSettingDto.kt`:  Dto class for `InstanceSetting` entity.
- `ReportDto.kt`: Dto class for `Report` entity.

### 7. `\config` Directory
- `WebAppConfig`: Set backend configuration required to be applied. (e.g. CORS configuration)
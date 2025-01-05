# Working with files

## Step-1: 
### Create a controller class FileOperationsController.java.
### Annotate FileOperationsController with @RestController and @RequestMapping("/api/v1/file") 
### Create a method and annotate it with @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
### Method should accept parameters @RequestParam("file") MultipartFile file (for rest-api) 
## Step-2:
### Create a service interface(FileOperationsService.java) and respective implementation class (SystemFileOperationsService.java)
### Annotate class with @Service
### Create method witch accepts MultipartFile type.

#### https://mina.apache.org/ftpserver-project/index.html


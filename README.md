## SpringBoot-MongoDB-Infinispan architecture

SpringBoot concept test integrated with MongoDB (using async driver) and Infinispan in memory data grid

	- Para hacer el build del proyecto ejecutar (necesita MongoDB levantado para pasar los tests)
		
> gradle build
		
	- Para lanzar la aplicación:
		
> java -jar platform-1.0-SNAPSHOT.jar
			
	- Para acceder al api rest mediante la URL:
		
> http://localhost:8080/api todas las peticiones http son redirigidas a peticiones seguras
		
	- Para descargar las dependencias de node, desde el directorio front/src/main es necesario ejecutar:
	
> npm install

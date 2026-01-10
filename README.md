**Steps to use the project **

1. run docker-compose file(cmd : docker compose up)
2. Ensure postgres volume path is okay in the docker-compose file(volumes:db-data:/var/lib/postgresql/data) // db creation might get skipped if previous data exist in the directory
3. Ensure database connection established and 'sisimpur' db been created and script file( ./db/init.sql:/docker-entrypoint-initdb.d/init.sql) file executed without error
4. open the bruno app and open ./bruno directory
5. use the apis

API List :
refactored the initial bruno api doc. Please make sure params and body properties spelled properly

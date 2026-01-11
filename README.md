**Steps to use the project **

1. run docker-compose file(cmd : docker compose up)
2. Ensure postgres volume path is okay in the docker-compose file(volumes:db-data:/var/lib/postgresql/data)
3. DB creation might get skipped if previous data exist in the directory -- Use docker compose down -v command to completely remove previously mounted container and data
4. Ensure database connection established and 'sisimpur' db been created and script file( ./db/init.sql:/docker-entrypoint-initdb.d/init.sql) file executed without error
5. open the bruno app and open ./bruno directory
6. use the apis

API List :
refactored the initial bruno api doc. Please make sure params and body properties spelled properly

cd api-search
mvn clean install
docker build -t search-service . --no-cache

cd ../api-availability
mvn clean install
docker build -t availability-service . --no-cache

cd ..

docker-compose down
docker-compose build --no-cache
docker-compose up --force-recreate
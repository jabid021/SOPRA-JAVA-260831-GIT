docker build -t quest:angular .

docker run -d -p 90:80 --name quest-angular quest:angular

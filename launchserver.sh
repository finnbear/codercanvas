kill $(lsof -t -i:4500)
cd server/out/artifacts/canvas_server
nohup java -jar server.jar &

start cmd /k java -jar dist/SyncWatch.jar -m 10:24:32 master.txt
timeout 3
start cmd /k java -jar dist/SyncWatch.jar -s localhost:3000 10:24:27 slave0.txt
timeout 3
start cmd /k java -jar dist/SyncWatch.jar -s localhost:3001 10:24:25 slave1.txt
timeout 3
start cmd /k java -jar dist/SyncWatch.jar -s localhost:3003 10:25:37 slave2.txt
timeout 3
start cmd /k java -jar dist/SyncWatch.jar -s localhost:3002 10:24:51 slave3.txt



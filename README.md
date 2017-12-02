# Parser of server logs

[![Build Status](https://travis-ci.org/MasterOfTheU/log-parser.svg?branch=master)](https://travis-ci.org/MasterOfTheU/log-parser)
[![codecov](https://codecov.io/gh/MasterOfTheU/log-parser/branch/master/graph/badge.svg)](https://codecov.io/gh/MasterOfTheU/log-parser)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/fa73983ba7864718a01b7392598361ad)](https://www.codacy.com/app/MasterOfTheU/log-parser?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=MasterOfTheU/log-parser&amp;utm_campaign=Badge_Grade)
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/fa73983ba7864718a01b7392598361ad)](https://www.codacy.com/app/MasterOfTheU/log-parser?utm_source=github.com&utm_medium=referral&utm_content=MasterOfTheU/log-parser&utm_campaign=Badge_Coverage)

## Task
With usage of MongoDB DBMS create a database that stores web-server logs. Log includes source URL,
user IP, time stamp of the beginning of source visit and total time user spent on URL source.

Create a program that converts CSV log into JSON format. URL must contain fields URL, IP, timeStamp, timeSpent. Create queries
1) <strong> Using Mongo built-in searching tools </strong>
 
    - print ordered IP list of logs with given URL
    - print ordered URL list of logs in the period of time
    - print ordered URL list of logs with given IP address 

2) <strong> Using Mongo built-in searching tools based on MapReduce paradigm </strong>

    - print descending ordered URL list of logs with total duration of URL visit
    - print descending ordered URL list of logs with total number to certain URL visits
    - print sorted list of logs in specified time period
    - print descending ordered IP list of logs sorted by spent time
    
Logs are stored in logs.csv file
![logs](https://user-images.githubusercontent.com/15348166/33517763-a179f4ec-d792-11e7-83e5-f56acd2b9272.PNG)

Results are given below:

## Ordered IP list of logs with given URL ("https://docs.oracle.com/")  <br>
![image](https://user-images.githubusercontent.com/15348166/33517774-cdafd324-d792-11e7-8d7a-e97d47829600.png)
## Ordered URL list of logs in the period of time ("10:10", "18:00") <br>
![image](https://user-images.githubusercontent.com/15348166/33517795-1dcc28e4-d793-11e7-9bd4-345614fbca07.png)
## Ordered URL list of logs with given IP address ("252.73.135.153") <br>
![image](https://user-images.githubusercontent.com/15348166/33517806-43bee302-d793-11e7-8664-0ea871fd4f28.png)

## Descending ordered URL list of logs with total duration of URL visit <br>
![image](https://user-images.githubusercontent.com/15348166/33517821-8a34e49e-d793-11e7-8903-610437c53942.png)
## Descending ordered URL list of logs with total number to certain URL visits <br>
![image](https://user-images.githubusercontent.com/15348166/33517832-b6d31a16-d793-11e7-9ba1-0645f730073a.png)
## Sorted list of logs in specified time period ("11:00", "18:00") <br>
![image](https://user-images.githubusercontent.com/15348166/33517839-d533efbc-d793-11e7-995c-e22d1c7c681b.png)
## Descending ordered IP list of logs sorted by spent time <br>
![image](https://user-images.githubusercontent.com/15348166/33517844-e691770c-d793-11e7-8d31-45acb0284e48.png)


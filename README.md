# Parser of server logs

[![Build Status](https://travis-ci.org/MasterOfTheU/log-parser.svg?branch=master)](https://travis-ci.org/MasterOfTheU/log-parser)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/fa73983ba7864718a01b7392598361ad)](https://www.codacy.com/app/MasterOfTheU/log-parser?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=MasterOfTheU/log-parser&amp;utm_campaign=Badge_Grade)

## Task
<p>With usage of MongoDB DBMS create a database that stores web-server logs. Log includes source URL,
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
    
Logs are stored in logs.csv file. Results are given below:
</p>


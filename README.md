To run this application you have to do following steps: 

1. Install MongoDB on your computer. 
2. Configure it and run on port 27017. 
3. Install some serwer, for example Tomcat. 
4. Download this project and configure Tomcat in Intellij.
5. Run. 

If upload file doesn't work, you should edit your tomcat conf/context.xml and change \<Context\> to \<Context allowCasualMultipartParsing="true"\>. Then restart server.

It should work, if not please write: passarinho4@gmail.com

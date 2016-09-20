CODE FLOW

The word-count implements 3 classes namely
1.	MAINCLASS.java
2.	READ.java
3.	COMPUTE.java
The execution starts from main() method implemented in MAINCLASS.java. path_of_file is used to read the path of the file entered by the user. file_name  is used to store this filename and length of the file is computed. 
Then the number of threads is entered by the user, which is used to split the files into chunks of certain bytes. 
The file then is read by the method readdata() which is implemented in READ.java class. Each chunk of file is then read, sufficient number of threads are created and these threads in turn process the files concurrently.

Execution:
1. JAVA SOURCE CODE

$ javac MAINCLASS.java
$ java MAINCLASS

UNCOMPRESS the OUTPUT FILE "wordcount-java" to see the output
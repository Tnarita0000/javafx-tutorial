CLASSPATH=.:./lib/mysql-connector-java-5.1.40/mysql-connector-java-5.1.40-bin.jar:./lib/jsch-0.1.54/jsch-0.1.54.jar:$PATH
export CLASSPATH


javac -d . src/controller/*.java
javac -d . src/app/App.java
java classes.App

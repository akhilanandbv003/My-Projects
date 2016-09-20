clear
sudo apt-get update

sudo add-apt-repository ppa:webupd8team/java
sudo apt-get update && sudo apt-get install oracle-jdk7-installer
sudo apt-get update

wget http://apache.mirrors.ionfish.org/spark/spark-1.6.0/spark-1.6.0-bin-hadoop2.6.tgz
tar -zxvf spark-1.6.0-bin-hadoop2.6.tgz
mv spark-1.6.0-bin-hadoop2.6 spark
exit 1
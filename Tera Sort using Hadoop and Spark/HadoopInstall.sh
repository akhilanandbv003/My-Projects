clear
sudo apt-get update

sudo add-apt-repository ppa:webupd8team/java
sudo apt-get update && sudo apt-get install oracle-jdk7-installer
sudo apt-get update

wget http://apache.claz.org/hadoop/common/hadoop-2.7.2/
tar -xzvf hadoop-2.7.2.tar.gz
mv hadoop-2.7.2 hadoop


exit 1
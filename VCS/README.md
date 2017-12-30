
```
mkdir vcsSparse
cd vcsSparse
git init
git remote add -f origin https://github.com/BrighterEyer/JavaEEDemos.git
git config core.sparsecheckout true
echo "VCS/" >> .git/info/sparse-checkout
git pull origin  master
cd VCS

mkdir -p /home/$USER/.m2/repository/org/hyperic/sigar/1.6.5.132/
mkdir -p /home/$USER/.m2/repository/net/sf/json-lib/json-lib/2.4/
mv sigar-1.6.5.132.jar  /home/$USER/.m2/repository/org/hyperic/sigar/1.6.5.132/
mv json-lib-2.4.jar  /home/$USER/.m2/repository/net/sf/json-lib/json-lib/2.4/

#数据库创建和导入
ls $(pwd)/src/main/resources/VCS.sql
# … sql路径
mysql -u root -p
source <sql路径>

#运行
mvn package
mvn jetty:run
```

```
用户名： admin1、admin2、admin3、admin4
密码全部为：000000
```
